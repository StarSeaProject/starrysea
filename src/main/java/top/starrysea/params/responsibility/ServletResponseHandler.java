package top.starrysea.params.responsibility;

import javax.servlet.http.HttpServletResponse;

public class ServletResponseHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof HttpServletResponse) {

		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return null;
	}

}
