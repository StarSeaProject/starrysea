package top.starrysea.params.responsibility;

import javax.servlet.http.HttpSession;

import top.starrysea.params.strategy.SessionToString;

public class HttpSessionHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof HttpSession) {
			changeToString = new SessionToString();
		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return changeToString.paramToString(object);
	}

}
