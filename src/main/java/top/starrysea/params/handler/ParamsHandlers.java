package top.starrysea.params.handler;

import static top.starrysea.common.Common.toJson;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

public class ParamsHandlers {

	private ParamsHandlers() {
	}

	static final UnaryOperator<Object> BINDING_RESULT = object -> {
		if (!(object instanceof BindingResult))
			return object;
		BindingResult bindingResult = (BindingResult) object;
		Map<String, Object> map = new HashMap<>();
		map.put("hasError", bindingResult.hasErrors());
		map.put("errorsCount", bindingResult.getErrorCount());
		return bindingResult.getClass().getSimpleName() + map.toString();
	};

	static final UnaryOperator<Object> DEVICE = object -> {
		if (!(object instanceof Device))
			return object;
		Device device = (Device) object;
		Map<String, Object> map = new HashMap<>();
		String type = "UNKNOWN";
		if (device.isNormal()) {
			type = "电脑";
		} else if (device.isMobile()) {
			type = "手机";
		} else if (device.isTablet()) {
			type = "平板";
		}
		map.put("deviceType", type);
		return device.getClass().getSimpleName() + map.toString();
	};

	static final UnaryOperator<Object> SESSION = object -> {
		if (!(object instanceof HttpSession))
			return object;
		HttpSession session = (HttpSession) object;
		Map<String, Object> map = new HashMap<>();
		Enumeration<String> enumNames = session.getAttributeNames();
		while (enumNames.hasMoreElements()) {
			String key = enumNames.nextElement();
			map.put(key, session.getAttribute(key));
		}
		return session.getClass().getSimpleName() + map.toString();
	};

	static final UnaryOperator<Object> REQUEST = object -> {
		if (!(object instanceof HttpServletRequest))
			return object;
		return "request对象";
	};

	static final UnaryOperator<Object> RESPONSE = object -> {
		if (!(object instanceof HttpServletResponse))
			return object;
		return "response对象";
	};

	static final UnaryOperator<Object> MULTIPART_FILE = object -> {
		if (!(object instanceof MultipartFile))
			return object;
		MultipartFile file = (MultipartFile) object;
		Map<String, Object> map = new HashMap<>();
		map.put("originalFilename", file.getOriginalFilename());
		map.put("contentType", file.getContentType());
		map.put("size", file.getSize());
		return file.getClass().getSimpleName() + map.toString();
	};

	static final UnaryOperator<Object> MULTIPART_FILES = object -> {
		if (!(object instanceof MultipartFile[]))
			return object;
		List<String> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		MultipartFile[] files = (MultipartFile[]) object;
		for (MultipartFile file : files) {
			map.put("originalFilename", file.getOriginalFilename());
			map.put("contentType", file.getContentType());
			map.put("size", file.getSize());
			list.add(map.toString());
		}

		return files.getClass().getSimpleName() + list.toString();
	};

	static final UnaryOperator<Object> DEFAULT = object -> {
		if (!(object instanceof String)) {
			return object.getClass().getSimpleName() + toJson(object);
		}
		return object;
	};

	public static Function<Object, Object> createHandler() {
		return BINDING_RESULT.andThen(DEVICE).andThen(SESSION).andThen(REQUEST).andThen(RESPONSE)
				.andThen(MULTIPART_FILE).andThen(MULTIPART_FILES).andThen(DEFAULT);
	}
}
