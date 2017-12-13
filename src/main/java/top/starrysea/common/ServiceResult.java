package top.starrysea.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceResult {

	private boolean successed = false;
	private Map<Class<?>, Object> theResult;
	private Integer nowPage;
	private Integer totalPage;
	private String errInfo;
	private Map<String, Object> extraInfo;

	public ServiceResult() {
		theResult = new HashMap<>();
	}

	public ServiceResult(String errInfo) {
		theResult = new HashMap<>();
		this.errInfo = errInfo;
	}

	public ServiceResult(DaoResult daoResult) {
		this.successed = daoResult.isSuccessed();
		this.theResult = daoResult.getTheResult();
		this.errInfo = daoResult.getErrInfo();
	}

	public boolean isSuccessed() {
		return successed;
	}

	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}

	public Object getTheResult() {
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

	public <T> T getResult(Class<T> type) {
		return type.cast(theResult.get(type));
	}

	public <T> void setResult(Class<T> type, T result) {
		if (type == null)
			throw new NullPointerException("类型不能为空");
		theResult.put(type, result);
	}

	public String toString() {
		if (this.successed) {
			if (this.theResult == null) {
				return "successed : " + this.successed;
			}
			if (this.theResult.get(List.class) != null) {
				StringBuilder result = new StringBuilder("successed : " + this.successed + "\n" + "result : \n");
				List<?> list = this.getResult(List.class);
				for (Object object : list) {
					result.append("\t" + object.toString() + "\n");
				}
				result.append("\n");
				return result.append("nowPage : " + this.nowPage + "\n" + "totalPage : " + this.totalPage).toString();
			} else {
				return "successed : " + this.successed + "\n" + "result : " + this.theResult;
			}
		} else {
			return "successed : " + this.successed + "\n" + "errInfo : " + this.errInfo;
		}
	}
}
