package top.starrysea.common;

import java.util.List;
import java.util.Map;

import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Admin;

public enum ResultKey {

	WOKR_LIST(List.class), WORK_DETAIL(Work.class), WORK_DETAIL_IMAGE(List.class), WORK_DETAIL_TYPE(
			List.class), WORK_TYPE_STOCK(Integer.class),

	ACTIVITY_LIST(List.class), NEWEST_ACTIVITY(Activity.class), ACTIVITY_DETAIL(Activity.class), ACTIVITY_FUNDING_LIST(
			List.class), ACTIVITY_FUNDING_THRESHOLD(Double.class),

	ORDER_LIST(List.class), ORDER_DETAIL(Orders.class), ORDER_DETAIL_LIST(List.class), ORDER_ADDRESS(Map.class),

	ADMIN(Admin.class),

	QUESTION_LIST(List.class);

	private Class<?> clazz;

	ResultKey(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
