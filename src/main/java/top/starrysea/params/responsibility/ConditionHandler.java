package top.starrysea.params.responsibility;

import java.util.HashMap;
import java.util.Map;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;

public class ConditionHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof Condition) {
			Condition condition = (Condition) object;
			Map<String, Object> map = new HashMap<>();
			if (condition.getPage() != null) {
				map.put("page", condition.getPage());
			}
			if (condition.getTimeEnd() != null) {
				map.put("timeEnd", condition.getTimeEnd());
			}
			if (condition.getTokenString() != null) {
				map.put("tokenString", condition.getTokenString());
			}
			if (condition.getExtraInfo() != null) {
				map.put("extraInfo", condition.getExtraInfo());
			}
			if (condition.getOrderDir() != null) {
				map.put("orderDir", condition.getOrderDir());
			}
			if (condition.getOrderBy() != null) {
				map.put("orderBy", condition.getOrderBy());
			}
			return condition.getClass().getSimpleName() + Common.toJson(map);
		}
		return nextHandler.handleRequest(object);
	}

}
