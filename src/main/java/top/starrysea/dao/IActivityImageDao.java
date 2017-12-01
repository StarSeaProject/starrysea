package top.starrysea.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;

public interface IActivityImageDao {

	DaoResult getAllActivityImageDao(Activity activity);

	DaoResult saveActivityImageDao(List<ActivityImage> activityImages);
}
