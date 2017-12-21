package top.starrysea.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IActivityDao;
import top.starrysea.dao.IActivityImageDao;
import top.starrysea.dao.IFundingDao;
import top.starrysea.file.FileCondition;
import top.starrysea.file.FileType;
import top.starrysea.file.FileUtil;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;
import top.starrysea.object.dto.Funding;
import top.starrysea.service.IActivityService;

import static top.starrysea.dao.impl.ActivityDaoImpl.PAGE_LIMIT;

@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IActivityDao activityDao;
	@Autowired
	private IActivityImageDao activityImageDao;
	@Autowired
	private IFundingDao fundingDao;
	@Autowired
	private FileUtil fileUtil;

	@Override
	// 查询所有众筹活动
	public ServiceResult queryAllActivityService(Condition condition, Activity activity) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = activityDao.getAllActivityDao(condition, activity);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		List<Activity> activitylist = daoResult.getResult(List.class);
		if (activitylist.isEmpty()) {
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

		daoResult = fundingDao.getAllFundingDao(new Funding.Builder().activity(activity).build());
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
		try {
			String originCoverFileName = fileUtil.saveFile(coverFile,
					FileCondition.of(FileType.IMG, 1, activity.getActivityName()));
			activity.setActivityCover(originCoverFileName);
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
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return new ServiceResult("插入失败,原因为" + e.getMessage());
		}
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
	@Transactional
	public ServiceResult addFundingService(List<Funding> fundings) {
		DaoResult daoResult = fundingDao.saveFundingDao(fundings);
		if (!daoResult.isSuccessed())
			throw new RuntimeException("添加众筹人失败");
		List<Activity> activitys = new ArrayList<>();
		for (Funding funding : fundings) {
			Activity activity = new Activity.Builder().activityMoney(funding.getFundingMoney())
					.activityId(funding.getActivity().getActivityId()).build();
			activitys.add(activity);
		}
		daoResult = activityDao.updateAddActivityMoneyDao(activitys);
		if (!daoResult.isSuccessed())
			throw new RuntimeException("更新总众筹金额失败");
		return new ServiceResult(daoResult);
	}

	@Override
	@Transactional
	public ServiceResult removeFundingService(Funding funding) {
		DaoResult daoResult = fundingDao.deleteFundingDao(funding);
		if (!daoResult.isSuccessed())
			throw new RuntimeException("删除众筹人失败");
		Activity activity = new Activity.Builder().activityMoney(funding.getFundingMoney())
				.activityId(funding.getActivity().getActivityId()).build();
		daoResult = activityDao.updateReduceActivityMoneyDao(activity);
		if (!daoResult.isSuccessed())
			throw new RuntimeException("更新总众筹金额失败");
		return new ServiceResult(daoResult);
	}

}
