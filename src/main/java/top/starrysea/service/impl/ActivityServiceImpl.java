package top.starrysea.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import top.starrysea.exception.UpdateException;
import top.starrysea.file.FileCondition;
import top.starrysea.file.FileType;
import top.starrysea.file.FileUtil;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;
import top.starrysea.object.dto.Funding;
import top.starrysea.service.IActivityService;

import static top.starrysea.dao.impl.ActivityDaoImpl.PAGE_LIMIT;
import static top.starrysea.common.Const.FUNDING_FACTOR;
import static top.starrysea.common.ResultKey.*;
import static top.starrysea.common.ServiceResult.SUCCESS_SERVICE_RESULT;

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
		DaoResult daoResult = activityDao.getNewestActivityDao();
		Activity a = daoResult.getResult(Activity.class);
		daoResult = activityDao.getAllActivityDao(condition, activity);
		@SuppressWarnings("unchecked")
		List<Activity> activitylist = daoResult.getResult(List.class);
		int totalPage = 0;
		daoResult = activityDao.getActivityCountDao(condition, activity);
		int count = daoResult.getResult(Integer.class);
		if (count % PAGE_LIMIT == 0)
			totalPage = count / PAGE_LIMIT;
		else
			totalPage = (count / PAGE_LIMIT) + 1;

		return ServiceResult.of(true).setResult(LIST_1, activitylist).setResult(ACTIVITY, a)
				.setNowPage(condition.getPage()).setTotalPage(totalPage);
	}

	@Override
	// 查询一个众筹活动的详情页
	public ServiceResult queryActivityService(Activity activity) {
		ServiceResult result = ServiceResult.of();
		DaoResult daoResult = activityDao.getActivityDao(activity);
		Activity a = daoResult.getResult(Activity.class);
		result.setSuccessed(true).setResult(ACTIVITY, a);
		daoResult = fundingDao.getAllFundingDao(new Funding.Builder().activity(activity).build());
		@SuppressWarnings("unchecked")
		List<Funding> fundings = daoResult.getResult(List.class);
		double fundingMoneySum = fundings.stream().collect(Collectors.summingDouble(Funding::getFundingMoney));
		double richThreshold = fundingMoneySum * FUNDING_FACTOR;
		List<Funding> richFundings = new ArrayList<>();
		List<Funding> normalFundings = new ArrayList<>();
		for (Funding funding : fundings) {
			if (funding.getFundingMoney() > richThreshold) {
				richFundings.add(funding);
			} else {
				normalFundings.add(funding);
			}
		}
		richFundings.addAll(normalFundings);
		result.setResult(LIST_1, richFundings).setResult(DOUBLE, richThreshold);
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
			activity.setActivityId(daoResult.getResult(Integer.class));
			for (ActivityImage activityImage : activityImages) {
				activityImage.setActivity(activity);
			}
			activityImageDao.saveActivityImageDao(activityImages);
			return SUCCESS_SERVICE_RESULT;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

	@Override
	// 修改一个众筹活动的状态
	public ServiceResult modifyActivityService(Activity activity) {
		activityDao.updateActivityDao(activity);
		return SUCCESS_SERVICE_RESULT;
	}

	@Override
	// 删除一个众筹活动
	public ServiceResult removeActivityService(Activity activity) {
		activityDao.deleteActivityDao(activity);
		return SUCCESS_SERVICE_RESULT;
	}

	@Override
	@Transactional
	public ServiceResult addFundingService(List<Funding> fundings) {
		try {
			fundingDao.saveFundingDao(fundings);
			List<Activity> activitys = new ArrayList<>();
			for (Funding funding : fundings) {
				Activity activity = new Activity.Builder().activityMoney(funding.getFundingMoney())
						.activityId(funding.getActivity().getActivityId()).build();
				activitys.add(activity);
			}
			activityDao.updateAddActivityMoneyDao(activitys);
			return SUCCESS_SERVICE_RESULT;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

	@Override
	@Transactional
	public ServiceResult removeFundingService(Funding funding) {
		try {
			fundingDao.deleteFundingDao(funding);
			Activity activity = new Activity.Builder().activityMoney(funding.getFundingMoney())
					.activityId(funding.getActivity().getActivityId()).build();
			activityDao.updateReduceActivityMoneyDao(activity);
			return SUCCESS_SERVICE_RESULT;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}

	}

}
