package top.starrysea.object.dto;

public class Online extends Entity {
	
	private String onlineId;
	private String onlineEmail;
	
	public Online() {
	}
	
	public Online(String onlineId, String onlineEmail) {
		this.onlineId = onlineId;
		this.onlineEmail = onlineEmail;
	}
	
	public String getOnlineId() {
		return onlineId;
	}
	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}
	public String getOnlineEmail() {
		return onlineEmail;
	}
	public void setOnlineEmail(String onlineEmail) {
		this.onlineEmail = onlineEmail;
	}
	
	
}
