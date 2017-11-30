package top.starrysea.common;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.starrysea.object.dto.Entity;

public class Common {

	private final static Logger logger = LoggerFactory.getLogger(Common.class);
	private static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
		return dateSdf.format(date);
	}

	public static String time2String(Date date) {
		return timeSdf.format(date);
	}

	public static Date string2Date(String str) {
		try {
			return dateSdf.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static Date string2Time(String str) {
		try {
			return timeSdf.parse(str);
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
		return getCharId(new String(), 10);
	}

	public static String getCharId(int size) {
		return getCharId(new String(), size);
	}

	public static String getCharId(String pre, int size) {
		StringBuffer theResult = new StringBuffer();
		theResult.append(pre);
		String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < size - pre.length(); i++) {
			int rand = (int) (Math.random() * a.length());
			theResult.append(a.charAt(rand));
		}
		return theResult.toString();
	}

	public static short getRandom(int randomRange) {
		Random random = new Random();
		return (short) random.nextInt(randomRange);
	}

	public static boolean isNotNull(Object object) {
		boolean result = false;
		if (object == null)
			return result;
		if (object instanceof String) {
			String temp = (String) object;
			if (temp != null && !temp.equals(""))
				result = true;
			else
				result = false;
		} else if (object instanceof Entity) {
			result = (object != null ? true : false);
		} else if (object instanceof List) {
			List<?> list = List.class.cast(object);
			if (list.size() > 0)
				result = true;
			else
				result = false;
		} else if (object instanceof Integer) {
			int num = (int) object;
			if (num == 0)
				result = false;
			else
				result = true;
		} else if (object instanceof Short) {
			short num = (short) object;
			if (num == 0)
				result = false;
			else
				result = true;
		}
		return result;
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
		List<String> list = new ArrayList<String>();
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
		List<String> errInfo = bindingResult.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		modelAndView.addObject("errInfo", errInfo);
		modelAndView.setViewName("error");
		return modelAndView;
	}
}
