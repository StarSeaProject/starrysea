package top.starrysea.params.responsibility;

import static top.starrysea.common.Common.toJson;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class HttpSessionHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof HttpSession) {
			HttpSession session = (HttpSession) object;
			Map<String, Object> map = new HashMap<>();
			Enumeration<String> enumNames = session.getAttributeNames();
			while (enumNames.hasMoreElements()) {
				String key = enumNames.nextElement();
				map.put(key, session.getAttribute(key));
			}
			return session.getClass().getSimpleName() + toJson(map);
		}
		return nextHandler.handleRequest(object);
	}

}
