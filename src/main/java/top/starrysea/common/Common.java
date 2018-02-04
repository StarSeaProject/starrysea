package top.starrysea.common;

import static top.starrysea.common.Const.ERRINFO;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.starrysea.kql.entity.Entity;

public class Common {

	private static final Logger logger = LoggerFactory.getLogger(Common.class);
	private static final Common INSTANCE = new Common();
	private SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 私有构造器防止外部创建新的Util对象
	private Common() {
	}

	public static String toJson(Object target) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(target);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return "";
	}

	public static String date2String(Date date) {
		if (date == null)
			return "";
		return INSTANCE.dateSdf.format(date);
	}

	public static String time2String(Date date) {
		if (date == null)
			return "";
		return INSTANCE.timeSdf.format(date);
	}

	public static Date string2Date(String str) {
		try {
			return INSTANCE.dateSdf.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static Date string2Time(String str) {
		try {
			return INSTANCE.timeSdf.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String getNowDate() {
		return date2String(new Date());
	}

	public static String getNowTime() {
		return time2String(new Date());
	}

	public static String getBefore(long time) {
		return time2String(new Date(System.currentTimeMillis() - time));
	}

	public static String getCharId() {
		return getCharId("", 10);
	}

	public static String getCharId(int size) {
		return getCharId("", size);
	}

	public static String getCharId(String pre, int size) {
		StringBuilder theResult = new StringBuilder();
		theResult.append(pre);
		String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		for (int i = 0; i < size - pre.length(); i++) {
			int rand = random.nextInt(a.length());
			theResult.append(a.charAt(rand));
		}
		return theResult.toString();
	}

	public static boolean isNotNull(Object object) {
		if (object == null)
			return false;
		if (object instanceof String) {
			String temp = (String) object;
			return !temp.equals("");
		}
		if (object instanceof Entity) {
			return true;
		}
		if (object instanceof List) {
			List<?> list = List.class.cast(object);
			return !list.isEmpty();
		}
		if (object instanceof Integer) {
			int num = (int) object;
			return num == 0;
		}
		if (object instanceof Short) {
			short num = (short) object;
			return num == 0;
		}
		throw new IllegalArgumentException("无法识别的参数类型");
	}

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return "";
		}
	}

	public static String getFieldErrors(BindingResult bindingResult) {
		List<String> list = new ArrayList<>();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			list.add(fieldError.getDefaultMessage());
		}
		return toJson(list);
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return clazz.cast(new Object());
	}

	public static ModelAndView handleVaildError(BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		String errInfo = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining("\n"));
		modelAndView.addObject("errInfo", errInfo);
		modelAndView.setViewName("error");
		return modelAndView;
	}

	public static Map<String, Object> handleVaildErrorForAjax(BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		String errInfo = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining("\n"));
		theResult.put(ERRINFO, errInfo);
		return theResult;
	}
}
