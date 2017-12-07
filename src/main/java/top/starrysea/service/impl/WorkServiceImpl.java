package top.starrysea.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IWorkDao;
import top.starrysea.object.dto.Work;
import top.starrysea.service.IMailService;
import top.starrysea.service.IWorkService;

import static top.starrysea.dao.impl.WorkDaoImpl.PAGE_LIMIT;

@Service("workService")
public class WorkServiceImpl implements IWorkService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IWorkDao workDao;
	@Autowired
	private IMailService mailService;

	private static final String FILE_ROOT = "D:" + File.separator + "starrysea" + File.separator;
	private static final String PDF_PATH_PREFIX = "http://120.79.24.63/";

	@Override
	// 查询所有作品
	public ServiceResult queryAllWorkService(Condition condition, Work work) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = workDao.getAllWorkDao(condition, work);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		List<Work> workList = daoResult.getResult(List.class);
		if (workList.size() == 0) {
			return new ServiceResult("查询结果为空");
		}
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
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		Work w = daoResult.getResult(Work.class);
		result.setSuccessed(true);
		result.setResult(Work.class, w);
		return result;
	}

	@Override
	// 添加一个作品
	public ServiceResult addWorkService(MultipartFile pdfFile, MultipartFile coverFile, Work work) {
		if (pdfFile.isEmpty()) {
			return new ServiceResult("pdf文件为空文件");
		}
		if (coverFile.isEmpty()) {
			return new ServiceResult("封面文件为空文件");
		}
		String pdfFileType = pdfFile.getOriginalFilename().substring(pdfFile.getOriginalFilename().lastIndexOf(".") + 1);
		if (!pdfFileType.equalsIgnoreCase("pdf")) {
			return new ServiceResult("pdf文件格式不合法");
		}
		String coverFileType = coverFile.getOriginalFilename().substring(coverFile.getOriginalFilename().lastIndexOf(".") + 1);
		if (!coverFileType.equalsIgnoreCase("jpg") && !coverFileType.equalsIgnoreCase("jpeg")
				&& !coverFileType.equalsIgnoreCase("png")) {
			return new ServiceResult("封面文件格式不合法,仅支持jpg/jpeg/png格式");
		}

		double fileSize = (double) pdfFile.getSize() / (double) (1024 * 1024);
		if (fileSize > 10) {
			return new ServiceResult("pdf文件不得超过10M!");
		}
		fileSize = (double) coverFile.getSize() / (double) (1024 * 1024);
		if (fileSize > 1) {
			return new ServiceResult("封面文件不得超过1M!");
		}

		String originPdfFileName = work.getWorkName() + Common.getCharId(5) + ".pdf";
		String pdfFilePath = FILE_ROOT + originPdfFileName;
		String originCoverFileName = work.getWorkName() + Common.getCharId(5) + "." + coverFileType;
		String coverFilePath = FILE_ROOT + originCoverFileName;
		try {
			FileCopyUtils.copy(pdfFile.getInputStream(), new FileOutputStream(pdfFilePath));
			FileCopyUtils.copy(coverFile.getInputStream(), new FileOutputStream(coverFilePath));
			work.setWorkUploadTime(Common.getNowDate());
			work.setWorkPdfpath("/" + originPdfFileName);
			work.setWorkCover("/" + originCoverFileName);
			DaoResult daoResult = workDao.saveWorkDao(work);
			if (!daoResult.isSuccessed()) {
				throw new RuntimeException("插入作品失败");
			}
			mailService.sendMailService(work);
			return new ServiceResult(daoResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ServiceResult("文件上传失败");
		}
	}

	@Override
	// 删除一个作品
	public ServiceResult removeWorkService(Work work) {
		return new ServiceResult(workDao.deleteWorkDao(work));
	}

}
