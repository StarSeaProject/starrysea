package top.starrysea.common;

import java.util.EnumMap;
import java.util.Map;

public class ServiceResult {

	public static final ServiceResult SUCCESS_SERVICE_RESULT = new ServiceResult(true);
	public static final ServiceResult FAIL_SERVICE_RESULT = new ServiceResult(false);

	private boolean successed = false;
	private Map<ResultKey, Object> theResult;
	private Integer nowPage;
	private Integer totalPage;
	private String errInfo;
	private Map<String, Object> extraInfo;

	public static ServiceResult of() {
		return new ServiceResult();
	}

	public static ServiceResult of(boolean success) {
		return new ServiceResult(success);
	}

	public static ServiceResult fail() {
		return new ServiceResult(false);
	}

	public static ServiceResult of(String errInfo) {
		return new ServiceResult(errInfo);
	}

	private ServiceResult() {
		theResult = new EnumMap<>(ResultKey.class);
	}

	private ServiceResult(boolean successed) {
		theResult = new EnumMap<>(ResultKey.class);
		this.successed = successed;
	}

	private ServiceResult(String errInfo) {
		theResult = new EnumMap<>(ResultKey.class);
		this.errInfo = errInfo;
	}

	public boolean isSuccessed() {
		return successed;
	}

	public ServiceResult setSuccessed(boolean successed) {
		checkModifyAccess();
		this.successed = successed;
		return this;
	}

	public Map<ResultKey, Object> getTheResult() {
		return theResult;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public ServiceResult setErrInfo(String errInfo) {
		checkModifyAccess();
		this.errInfo = errInfo;
		return this;
	}

	public Integer getNowPage() {
		return nowPage;
	}

	public ServiceResult setNowPage(Integer nowPage) {
		checkModifyAccess();
		this.nowPage = nowPage;
		return this;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public ServiceResult setTotalPage(Integer totalPage) {
		checkModifyAccess();
		this.totalPage = totalPage;
		return this;
	}

	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}

	public ServiceResult setExtraInfo(Map<String, Object> extraInfo) {
		checkModifyAccess();
		this.extraInfo = extraInfo;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T getResult(ResultKey resultKey) {
		return (T) resultKey.getClazz().cast(theResult.get(resultKey));
	}

	public <T> ServiceResult setResult(ResultKey resultKey, T result) {
		checkModifyAccess();
		if (!resultKey.getClazz().isAssignableFrom(result.getClass()))
			throw new IllegalArgumentException("传入的result不是" + resultKey.getClazz().getName() + "类型");
		theResult.put(resultKey, result);
		return this;
	}

	private void checkModifyAccess() {
		if (this == SUCCESS_SERVICE_RESULT || this == FAIL_SERVICE_RESULT)
			throw new UnsupportedOperationException(
					"不可以对SUCCESS_SERVICE_RESULT或FAIL_SERVICE_RESULT进行修改,应使用ServiceResult.of(boolean)");
	}
}
