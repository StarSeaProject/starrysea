package top.starrysea.params.responsibility;

import top.starrysea.params.strategy.ObjectToString;

/*
 * 具体职责者
 */
public class DefaultHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		return new ObjectToString().paramToString(object);
	}

}
