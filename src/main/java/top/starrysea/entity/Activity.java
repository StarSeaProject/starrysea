package top.starrysea.entity;

public class Activity extends Entity {
	private Integer activityId;
	private String activityName;
	private String activityContent;
	private short activityStatus;
	private String activityQcode;
	public Activity() {
	}
	public Activity(Integer activityId, String activityName, String activityContent, short activityStatus,
			String activityQcode) {
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityContent = activityContent;
		this.activityStatus = activityStatus;
		this.activityQcode = activityQcode;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public short getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(short activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getActivityQcode() {
		return activityQcode;
	}
	public void setActivityQcode(String activityQcode) {
		this.activityQcode = activityQcode;
	}
	
}
