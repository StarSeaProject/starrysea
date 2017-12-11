package top.starrysea.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;
import top.starrysea.object.dto.Funding;

public interface IActivityService {
	ServiceResult queryAllActivityService(Condition condition, Activity activity);

	ServiceResult queryActivityService(Activity activity);

	ServiceResult addActivityService(MultipartFile coverFile, Activity activity, List<ActivityImage> activityImages);

	ServiceResult modifyActivityService(Activity activity);

	ServiceResult removeActivityService(Activity activity);
	
	ServiceResult addFundingService(List<Funding> fundings);
	
	ServiceResult removeFundingService(Funding funding);
}
