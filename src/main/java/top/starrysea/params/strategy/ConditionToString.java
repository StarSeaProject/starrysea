package top.starrysea.params.strategy;

import top.starrysea.common.Condition;

/*
 * 具体策略算法
 */
public class ConditionToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		Condition condition = (Condition) object;
		return "Condition{\"page\":" + condition.getPage() + "}";
	}

}
