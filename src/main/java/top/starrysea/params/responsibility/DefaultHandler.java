package top.starrysea.params.responsibility;

import static top.starrysea.common.Common.toJson;

/*
 * 具体职责者
 */
public class DefaultHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		return object.getClass().getSimpleName() + toJson(object);
	}

}
