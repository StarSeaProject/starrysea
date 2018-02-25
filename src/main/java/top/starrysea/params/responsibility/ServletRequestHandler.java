package top.starrysea.params.responsibility;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof HttpServletRequest) {
			return "request对象";
		}
		return nextHandler.handleRequest(object);
	}

}
