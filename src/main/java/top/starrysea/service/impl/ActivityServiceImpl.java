package top.starrysea.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IActivityDao;
import top.starrysea.dao.IActivityImageDao;
import top.starrysea.dao.IFundingDao;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;
import top.starrysea.object.dto.Funding;
import top.starrysea.service.IActivityService;

import static top.starrysea.common.Common.FILE_ROOT;
import static top.starrysea.dao.impl.ActivityDaoImpl.PAGE_LIMIT;

@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private IActivityDao activityDao;
	@Autowired
	private IActivityImageDao activityImageDao;
	@Autowired
	private IFundingDao fundingDao;

	@Override
	// 查询所有众筹活动
	public ServiceResult queryAllActivityService(Condition condition, Activity activity) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = activityDao.getAllActivityDao(condition, activity);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		List<Activity> activitylist = daoResult.getResult(List.class);
		if (activitylist.size() == 0) {
			return new ServiceResult("查询结果为空");
		}
		int totalPage = 0;
		daoResult = activityDao.getActivityCountDao(condition, activity);
		int count = daoResult.getResult(Integer.class);
		if (count % PAGE_LIMIT == 0)
			totalPage = count / PAGE_LIMIT;
		else
			totalPage = (count / PAGE_LIMIT) + 1;

		result.setSuccessed(true);
		result.setResult(List.class, activitylist);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}

	@Override
	// 查询一个众筹活动的详情页
	public ServiceResult queryActivityService(Activity activity) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = activityDao.getActivityDao(activity);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		Activity a = daoResult.getResult(Activity.class);
		result.setSuccessed(true);
		result.setResult(Activity.class, a);
		
		daoResult=fundingDao.getAllFundingDao(new Funding.Builder().activity(activity).build());
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		result.setResult(List.class, daoResult.getResult(List.class));
		return result;
	}

	@Override
	// 添加一个众筹活动
	@Transactional
	public ServiceResult addActivityService(MultipartFile coverFile, Activity activity,
			List<ActivityImage> activityImages) {
		if (coverFile.isEmpty()) {
			return new ServiceResult("封面文件为空文件");
		}
		String coverFileType = coverFile.getOriginalFilename()
				.substring(coverFile.getOriginalFilename().lastIndexOf(".") + 1);
		if (!coverFileType.equalsIgnoreCase("jpg") && !coverFileType.equalsIgnoreCase("jpeg")
				&& !coverFileType.equalsIgnoreCase("png")) {
			return new ServiceResult("封面文件格式不合法,仅支持jpg/jpeg/png格式");
		}
		double fileSize = (double) coverFile.getSize() / (double) (1024 * 1024);
		if (fileSize > 1) {
			return new ServiceResult("封面文件不得超过1M!");
		}
		String originCoverFileName = activity.getActivityName() + Common.getCharId(5) + "." + coverFileType;
		String coverFilePath = FILE_ROOT + originCoverFileName;
		try {
			FileCopyUtils.copy(coverFile.getInputStream(), new FileOutputStream(coverFilePath));
			activity.setActivityCover("/" + originCoverFileName);
			activity.setActivityStatus((short) 1);
			DaoResult daoResult = activityDao.saveActivityDao(activity);
			if (!daoResult.isSuccessed()) {
				return new ServiceResult(daoResult);
			}
			activity.setActivityId(daoResult.getResult(Integer.class));
			for (ActivityImage activityImage : activityImages) {
				activityImage.setActivity(activity);
			}
			return new ServiceResult(activityImageDao.saveActivityImageDao(activityImages));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ServiceResult("插入失败");
	}

	@Override
	// 修改一个众筹活动的状态
	public ServiceResult modifyActivityService(Activity activity) {
		return new ServiceResult(activityDao.updateActivityDao(activity));
	}

	@Override
	// 删除一个众筹活动
	public ServiceResult removeActivityService(Activity activity) {
		return new ServiceResult(activityDao.deleteActivityDao(activity));
	}

	@Override
	public ServiceResult addFundingService(List<Funding> fundings) {
		return new ServiceResult(fundingDao.saveFundingDao(fundings));
	}

	@Override
	public ServiceResult removeFundingService(Funding funding) {
		return new ServiceResult(fundingDao.deleteFundingDao(funding));
	}

}
