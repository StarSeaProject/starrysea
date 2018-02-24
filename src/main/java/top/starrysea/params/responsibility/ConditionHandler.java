package top.starrysea.params.responsibility;

import top.starrysea.common.Condition;
import top.starrysea.params.strategy.ConditionToString;

public class ConditionHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof Condition) {
			changeToString = new ConditionToString();
		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return changeToString.paramToString(object);
	}

}
