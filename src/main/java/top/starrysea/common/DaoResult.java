package top.starrysea.common;

public class DaoResult {

	private boolean successed;
	private Object result;
	private String errInfo;

	public DaoResult(boolean successed, Object result) {
		this.successed = successed;
		this.result = result;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
