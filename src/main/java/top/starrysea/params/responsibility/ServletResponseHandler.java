package top.starrysea.params.responsibility;

import javax.servlet.http.HttpServletResponse;

public class ServletResponseHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof HttpServletResponse) {
			return "response对象";
		}
		return nextHandler.handleRequest(object);
	}

}
