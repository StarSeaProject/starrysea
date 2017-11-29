package top.starrysea.service;

import java.util.List;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;

public interface IActivityService {
	ServiceResult queryAllActivityService(Condition condition, Activity activity);

	ServiceResult queryActivityService(Activity activity);

	ServiceResult addActivityService(Activity activity, List<ActivityImage> activityImages);

	ServiceResult modifyActivityService(Activity activity);

	ServiceResult removeActivityService(Activity activity);

}
