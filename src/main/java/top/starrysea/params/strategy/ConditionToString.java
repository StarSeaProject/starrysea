package top.starrysea.params.strategy;

import java.util.HashMap;
import java.util.Map;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;

/*
 * 具体策略算法
 */
public class ConditionToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
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

}
