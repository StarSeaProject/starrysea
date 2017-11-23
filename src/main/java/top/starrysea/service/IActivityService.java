package top.starrysea.service;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Activity;

public interface IActivityService {
	ServiceResult queryAllActivityService(Condition condition, Activity activity);

	ServiceResult queryActivityService(Activity activity);

	ServiceResult addActivityService(Activity activity);

	ServiceResult modifyActivityService(Activity activity);

	ServiceResult removeActivityService(Activity activity);

}
