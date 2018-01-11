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
import top.starrysea.exception.UpdateException;
import top.starrysea.file.FileCondition;
import top.starrysea.file.FileType;
import top.starrysea.file.FileUtil;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;
import top.starrysea.service.IWorkService;

import static top.starrysea.dao.impl.WorkDaoImpl.PAGE_LIMIT;

@Service("workService")
public class WorkServiceImpl implements IWorkService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IWorkDao workDao;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private IWorkImageDao workImageDao;

	@Override
	// 查询所有作品
	public ServiceResult queryAllWorkService(Condition condition, Work work) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = workDao.getAllWorkDao(condition, work);
		List<Work> workList = daoResult.getResult(List.class);
		int totalPage = 0;
		daoResult = workDao.getWorkCountDao(condition, work);
		int count = (int) daoResult.getResult(Integer.class);
		if (count % PAGE_LIMIT == 0) {
			totalPage = count / PAGE_LIMIT;
		} else {
			totalPage = (count / PAGE_LIMIT) + 1;
		}
		result.setSuccessed(true);
		result.setResult(List.class, workList);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}

	@Override
	// 查询一个作品的详情页
	public ServiceResult queryWorkService(Work work) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = workDao.getWorkDao(work);
		Work w = daoResult.getResult(Work.class);
		daoResult = workDao.getStockDao(work);
		Integer stock = daoResult.getResult(Integer.class);
		daoResult = workImageDao.getAllWorkImageDao(new WorkImage.Builder().work(work).build());
		result.setSuccessed(true);
		result.setResult(Work.class, w);
		result.setResult(Integer.class, stock);
		result.setResult(List.class, daoResult.getResult(List.class));
		return result;
	}

	@Override
	// 添加一个作品
	@Transactional
	public ServiceResult addWorkService(MultipartFile coverFile, MultipartFile[] imageFiles, Work work) {
		try {
			String originCoverFileName = fileUtil.saveFile(coverFile,
					FileCondition.of(FileType.IMG, 1, work.getWorkName()));
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
			ServiceResult serviceResult = new ServiceResult(true);
			serviceResult.setResult(Work.class, work);
			return serviceResult;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

	@Override
	// 删除一个作品
	public ServiceResult removeWorkService(Work work) {
		return new ServiceResult(workDao.deleteWorkDao(work));
	}

}
