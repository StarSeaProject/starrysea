package top.starrysea.params.strategy;

import static top.starrysea.common.Common.toJson;

/*
 * 具体策略算法
 */
public class ObjectToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		return object.getClass().getSimpleName() + toJson(object);
	}

}
