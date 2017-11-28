package top.starrysea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IActivityDao;
import top.starrysea.object.dto.Activity;
import top.starrysea.service.IActivityService;

import static top.starrysea.dao.impl.ActivityDaoImpl.PAGE_LIMIT;

@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private IActivityDao activityDao;

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
		return result;
	}

	@Override
	// 添加一个众筹活动
	public ServiceResult addActivityService(Activity activity) {
		activity.setActivityStatus((short) 1);
		return new ServiceResult(activityDao.saveActivityDao(activity));
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

}
