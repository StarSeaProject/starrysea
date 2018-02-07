package top.starrysea.common;

import java.util.List;
import java.util.Map;

import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Admin;

public enum ResultKey {

	LIST_1(List.class),LIST_2(List.class),
	
	ACTIVITY(Activity.class),ORDER(Orders.class),ADMIN(Admin.class),WORK(Work.class),
	
	DOUBLE(Double.class),
	
	MAP(Map.class);

	private Class<?> clazz;

	ResultKey(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
