package top.starrysea.strategy;

import org.springframework.beans.factory.annotation.Autowired;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.WorkForAdd;
import top.starrysea.object.view.in.WorkForAll;

/*
 * 逻辑控制
 */
public class ToStringContext {
	@Autowired
	private ChangeToString changeToString ;

	public String toString(Object object) {
		if (object instanceof OrderForAdd) {
			changeToString = new OAddToString();
		} else if (object instanceof WorkForAdd) {
			changeToString = new WAddToString();
		} else if (object instanceof Condition) {
			changeToString = new ConditionToString();
		} else if (object instanceof WorkForAll) {
			changeToString = new WAllToString();
		} else {
			changeToString = new ObjectToString();
		}
		return changeToString.paramToString(object);
	}
}
