package top.starrysea.params.handler;

import static top.starrysea.common.Common.toJson;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;

public class ParamsHandlers {

	private ParamsHandlers() {
	}

	static final UnaryOperator<Object> BINDING_RESULT = object -> {
		if(!(object instanceof BindingResult))
			return object;
		BindingResult bindingResult = (BindingResult) object;
		return bindingResult.getClass().getSimpleName() + "{\"hasError:\":" + bindingResult.hasErrors()
				+ ",\"errorsCount\":" + bindingResult.getErrorCount() + "}";
	};

	static final UnaryOperator<Object> DEVICE = object -> {
		if(!(object instanceof Device))
			return object;
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

	static final UnaryOperator<Object> SESSION = object -> {
		if(!(object instanceof Device))
			return object;
		HttpSession session = (HttpSession) object;
		Map<String, Object> map = new HashMap<>();
		Enumeration<String> enumNames = session.getAttributeNames();
		while (enumNames.hasMoreElements()) {
			String key = enumNames.nextElement();
			map.put(key, session.getAttribute(key));
		}
		return session.getClass().getSimpleName() + toJson(map);
	};

	static final UnaryOperator<Object> CONDITION = object -> {
		if(!(object instanceof Condition))
			return object;
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

	static final UnaryOperator<Object> REQUEST = object -> {
		if(!(object instanceof HttpServletRequest))
			return object;
		return "request对象";
	};

	static final UnaryOperator<Object> RESPONSE = object -> {
		if(!(object instanceof HttpServletResponse))
			return object;
		return "response对象";
	};

	static final UnaryOperator<Object> MULTIPART_FILE = object -> {
		if(!(object instanceof MultipartFile))
			return object;
		MultipartFile file = (MultipartFile) object;
		return file.getClass().getSimpleName() + "{\"originalFilename\":" + file.getOriginalFilename()
				+ ",\"contentType\":" + file.getContentType() + ",\"size\":" + file.getSize() + "}";
	};

	static final UnaryOperator<Object> MULTIPART_FILES = object -> {
		if(!(object instanceof MultipartFile[]))
			return object;
		ArrayList<String> list = new ArrayList<>();
		MultipartFile[] files = (MultipartFile[]) object;
		for (MultipartFile file : files) {
			String params = "{\"originalFilename\":" + file.getOriginalFilename() + ",\"contentType\":"
					+ file.getContentType() + ",\"size\"" + file.getSize() + "}";
			list.add(params);
		}

		return files.getClass().getSimpleName() + list.toString();
	};

	static final UnaryOperator<Object> DEFAULT = object -> object.getClass().getSimpleName() + toJson(object);
}
