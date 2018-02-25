package top.starrysea.params.handler;

import static top.starrysea.common.Common.toJson;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;

public class ParamsHandlers {

	private ParamsHandlers() {
	}

	public static final Function<Object,String> BINDING_RESULT = object -> {
		BindingResult bindingResult = (BindingResult) object;
		return bindingResult.getClass().getSimpleName() + "{\"hasError:\":" + bindingResult.hasErrors()
				+ ",\"errorsCount\":" + bindingResult.getErrorCount() + "}";
	};

	public static final Function<Object,String> DEVICE = object -> {
		Device device = (Device) object;
		String type = "UNKNOWN";
		if (device.isNormal()) {
			type = "电脑";
		} else if (device.isMobile()) {
			type = "手机";
		} else if (device.isTablet()) {
			type = "平板";
		}
		return device.getClass().getSimpleName() + "{\"deviceType\":" + type + "}";
	};

	public static final Function<Object,String> SESSION = object -> {
		HttpSession session = (HttpSession) object;
		Map<String, Object> map = new HashMap<>();
		Enumeration<String> enumNames = session.getAttributeNames();
		while (enumNames.hasMoreElements()) {
			String key = enumNames.nextElement();
			map.put(key, session.getAttribute(key));
		}
		return session.getClass().getSimpleName() + toJson(map);
	};

	public static final Function<Object,String> CONDITION = object -> {
		Condition condition = (Condition) object;
		Map<String, Object> map = new HashMap<>();
		if (condition.getPage() != null) {
			map.put("page", condition.getPage());
		}
		if (condition.getTimeEnd() != null) {
			map.put("timeEnd", condition.getTimeEnd());
		}
		if (condition.getTokenString() != null) {
			map.put("tokenString", condition.getTokenString());
		}
		if (condition.getExtraInfo() != null) {
			map.put("extraInfo", condition.getExtraInfo());
		}
		if (condition.getOrderDir() != null) {
			map.put("orderDir", condition.getOrderDir());
		}
		if (condition.getOrderBy() != null) {
			map.put("orderBy", condition.getOrderBy());
		}
		return condition.getClass().getSimpleName() + Common.toJson(map);
	};

	public static final Function<Object,String> REQUEST = object -> "request对象";

	public static final Function<Object,String> RESPONSE = object -> "response对象";

	public static final Function<Object,String> MULTIPART_FILE = object -> {
		MultipartFile file = (MultipartFile) object;
		return file.getClass().getSimpleName() + "{\"originalFilename\":" + file.getOriginalFilename()
				+ ",\"contentType\":" + file.getContentType() + ",\"size\":" + file.getSize() + "}";
	};

	public static final Function<Object,String> MULTIPART_FILES = object -> {
		ArrayList<String> list = new ArrayList<>();
		MultipartFile[] files = (MultipartFile[]) object;
		for (MultipartFile file : files) {
			String params = "{\"originalFilename\":" + file.getOriginalFilename() + ",\"contentType\":"
					+ file.getContentType() + ",\"size\"" + file.getSize() + "}";
			list.add(params);
		}

		return files.getClass().getSimpleName() + list.toString();
	};

	public static final Function<Object,String> DEFAULT = object -> object.getClass().getSimpleName() + toJson(object);
}
