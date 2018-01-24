package top.starrysea.common;

import java.util.List;

import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Admin;

public enum ResultKey {

	WOKR_LIST(List.class, "用于WorkServiceImpl.queryAllWorkService()"), WORK_DETAIL(Work.class,
			"用于WorkServiceImpl.queryWorkService/removeWorkService()"), WORK_DETAIL_IMAGE(List.class,
					"用于WorkServiceImpl.queryWorkService()"), WORK_DETAIL_TYPE(List.class,
							"用于WorkServiceImpl.queryWorkService()"),

	ACTIVITY_LIST(List.class, "用于ActivityServiceImpl.queryAllActivityService()"), NEWEST_ACTIVITY(Activity.class,
			"用于ActivityServiceImpl.queryAllActivityService()"), ACTIVITY_DETAIL(Activity.class,
					"用于ActivityServiceImpl.queryActivityService()"), ACTIVITY_FUNDING_LIST(List.class,
							"用于ActivityServiceImpl.queryActivityService()"), ACTIVITY_FUNDING_THRESHOLD(Double.class,
									"用于ActivityServiceImpl.queryActivityService()"),

	ORDER_LIST(List.class, "用于OrderServiceImpl.queryAllOrderService()"), ORDER_DETAIL(Orders.class,
			"用于OrderServiceImpl.queryOrderService()"),

	ADMIN(Admin.class, "用于UserServiceImpl.loginService()");

	private Class<?> clazz;
	private String caption;

	ResultKey(Class<?> clazz, String caption) {
		this.clazz = clazz;
		this.caption = caption;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getCaption() {
		return caption;
	}

}
