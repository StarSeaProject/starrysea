package top.starrysea.params.strategy;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import static top.starrysea.common.Common.toJson;;

public class SessionToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		HttpSession session = (HttpSession) object;
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> enumNames = session.getAttributeNames();
		while (enumNames.hasMoreElements()) {
			String key = enumNames.nextElement();
			map.put(key, session.getAttribute(key));
		}
		return session.getClass().getSimpleName() + toJson(map);
	}

}
