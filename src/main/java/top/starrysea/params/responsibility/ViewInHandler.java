package top.starrysea.params.responsibility;

import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.WorkForAdd;
import top.starrysea.params.strategy.OAddToString;
import top.starrysea.params.strategy.WAddToString;
import top.starrysea.params.strategy.WAllToString;

/*
 * 具体职责者
 */
public class ViewInHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof OrderForAdd) {
			changeToString = new OAddToString();
		} else if (object instanceof WorkForAdd) {
			changeToString = new WAddToString();
		} else if (object instanceof WAllToString) {
			changeToString = new WAllToString();
		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return changeToString.paramToString(object);
	}

}
