package top.starrysea.object.bo;

public class ModifyAddressForEmail {
	private String areaName;
	private String areaAddress;
	private long limitTime;

	public ModifyAddressForEmail(String areaName, String areaAddress, long limitTime) {
		this.areaName = areaName;
		this.areaAddress = areaAddress;
		this.limitTime = limitTime;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getAreaAddress() {
		return areaAddress;
	}

	public long getLimitTime() {
		return limitTime;
	}

}
