package top.starrysea.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.starrysea.kql.entity.Entity;

public class DaoResult {

	private boolean successed;
	private Map<Class<?>, Object> theResult;
	private String errInfo;

	public DaoResult(boolean successed) {
		this.successed = successed;
	}

	public DaoResult(boolean successed, Map<?,?> result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put(Map.class, result);
	}
	public DaoResult(boolean successed, List<?> result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put(List.class, result);
	}

	public DaoResult(boolean successed, Entity result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put(result.getClass(), result);
	}

	public DaoResult(boolean successed, Integer result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put(Integer.class, result);
	}
	
	public DaoResult(boolean successed, boolean result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put(Boolean.class, result);
	}

	public DaoResult(boolean result, String errInfo) {
		this.successed = result;
		this.errInfo = errInfo;
	}

	public boolean isSuccessed() {
		return successed;
	}

	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public Map<Class<?>, Object> getTheResult() {
		return theResult;
	}

	public <T> T getResult(Class<T> type) {
		return type.cast(theResult.get(type));
	}

}
