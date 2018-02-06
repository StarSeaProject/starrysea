package top.starrysea.common;

import java.util.EnumMap;
import java.util.Map;

public class ServiceResult {

	private boolean successed = false;
	private Map<ResultKey, Object> theResult;
	private Integer nowPage;
	private Integer totalPage;
	private String errInfo;
	private Map<String, Object> extraInfo;

	public ServiceResult() {
		theResult = new EnumMap<>(ResultKey.class);
	}

	public ServiceResult(boolean successed) {
		theResult = new EnumMap<>(ResultKey.class);
		this.successed = successed;
	}

	public ServiceResult(String errInfo) {
		theResult = new EnumMap<>(ResultKey.class);
		this.errInfo = errInfo;
	}

	public boolean isSuccessed() {
		return successed;
	}

	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}

	public Map<ResultKey, Object> getTheResult() {
		return theResult;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public Integer getNowPage() {
		return nowPage;
	}

	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(Map<String, Object> extraInfo) {
		this.extraInfo = extraInfo;
	}

	@SuppressWarnings("unchecked")
	public <T> T getResult(ResultKey resultKey) {
		return (T) resultKey.getClazz().cast(theResult.get(resultKey));
	}

	public <T> void setResult(ResultKey resultKey, T result) {
		if (!resultKey.getClazz().isAssignableFrom(result.getClass()))
			throw new IllegalArgumentException("传入的result不是" + resultKey.getClazz().getName() + "类型");
		theResult.put(resultKey, result);
	}

}
