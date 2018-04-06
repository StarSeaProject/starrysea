package top.starrysea.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IWorkDao;
import top.starrysea.dao.IWorkImageDao;
import top.starrysea.dao.IWorkTypeDao;
import top.starrysea.exception.UpdateException;
import top.starrysea.file.FileCondition;
import top.starrysea.file.FileType;
import top.starrysea.file.FileUtil;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;
import top.starrysea.object.dto.WorkType;
import top.starrysea.service.IWorkService;

import static top.starrysea.dao.impl.WorkDaoImpl.PAGE_LIMIT;
import static top.starrysea.common.ResultKey.*;
import static top.starrysea.common.ServiceResult.SUCCESS_SERVICE_RESULT;

@Service("workService")
public class WorkServiceImpl implements IWorkService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IWorkDao workDao;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private IWorkImageDao workImageDao;
	@Autowired
	private IWorkTypeDao workTypeDao;

	@Override
	// 查询所有作品
	public ServiceResult queryAllWorkService(Condition condition, Work work) {
		DaoResult daoResult = workDao.getAllWorkDao(condition, work);
		@SuppressWarnings("unchecked")
		List<Work> workList = daoResult.getResult(List.class);
		int totalPage = 0;
		daoResult = workDao.getWorkCountDao(condition, work);
		int count = (int) daoResult.getResult(Integer.class);
		if (count % PAGE_LIMIT == 0) {
			totalPage = count / PAGE_LIMIT;
		} else {
			totalPage = (count / PAGE_LIMIT) + 1;
		}

		return ServiceResult.of(true).setResult(LIST_1, workList).setNowPage(condition.getPage())
				.setTotalPage(totalPage);
	}

	@Override
	// 查询一个作品的详情页
	public ServiceResult queryWorkService(Work work) {
		workDao.addWorkClick(work);
		DaoResult daoResult = workDao.getWorkDao(work);
		ServiceResult result = ServiceResult.of(true);
		result.setResult(WORK, daoResult.getResult(Work.class));
		daoResult = workImageDao.getAllWorkImageDao(new WorkImage.Builder().work(work).build());
		result.setResult(LIST_1, daoResult.getResult(List.class));
		daoResult = workTypeDao.getAllWorkTypeDao(new WorkType.Builder().work(work).build());
		result.setResult(LIST_2, daoResult.getResult(List.class));
		return result;
	}

	@Override
	// 添加一个作品
	@Transactional
	public ServiceResult addWorkService(MultipartFile coverFile, MultipartFile[] imageFiles, Work work,
			List<WorkType> workTypes) {
		try {
			String originCoverFileName = fileUtil.saveFile(coverFile,
					FileCondition.of(FileType.IMG, 1, "work_" + work.getWorkId() + "_"));
			work.setWorkUploadTime(Common.getNowDate());
			work.setWorkCover(originCoverFileName);
			DaoResult daoResult = workDao.saveWorkDao(work);
			work.setWorkId(daoResult.getResult(Integer.class));
			List<WorkImage> workImages = new ArrayList<>();
			for (MultipartFile imageFile : imageFiles) {
				String originImageFileName = fileUtil.saveFile(imageFile,
						FileCondition.of(FileType.IMG, 1, work.getWorkName()));
				workImages.add(new WorkImage.Builder().work(work).workImagePath(originImageFileName).build());
			}
			workImageDao.saveWorkImageDao(workImages);
			for (WorkType workType : workTypes) {
				workType.setWork(work);
			}
			workTypeDao.saveWorkTypeDao(workTypes);
			return ServiceResult.of(true).setResult(WORK, work);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

	@Override
	// 删除一个作品
	public ServiceResult removeWorkService(Work work) {
		workDao.deleteWorkDao(work);
		return SUCCESS_SERVICE_RESULT;
	}

	@Override
	public ServiceResult removeWorkTypeService(WorkType workType) {
		workTypeDao.deleteWorkTypeDao(workType);
		return SUCCESS_SERVICE_RESULT;
	}

	@Override
	public ServiceResult modifyWorkTypeService(WorkType workType) {
		workTypeDao.updateWorkTypeStockDao(workType);
		return SUCCESS_SERVICE_RESULT;
	}

}
