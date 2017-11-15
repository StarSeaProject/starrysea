package top.starrysea.dao;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.entity.Activity;

public interface IActivityDao {
	DaoResult getAllActivityDao(Condition condition, Activity activity);

	DaoResult getActivityCountDao(Condition condition, Activity activity);

	DaoResult getActivityDao(Activity activity);

	DaoResult saveActivityDao(Activity activity);

	DaoResult updateActivityDao(Activity activity);

	DaoResult deleteActivityDao(Activity activity);
}
