package top.starrysea.params.responsibility;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof HttpServletRequest) {

		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return null;
	}

}
