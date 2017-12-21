package top.starrysea.object.view.out;

public class ActivityForOne {

	private String activityName;
	private String activityContent;
	private Short activityStatus;
	private Double activityMoney;

	public ActivityForOne(String activityName, String activityContent, Short activityStatus,Double activityMoney) {
		this.activityName = activityName;
		this.activityContent = activityContent;
		this.activityStatus = activityStatus;
		this.activityMoney=activityMoney;
	}

	public String getActivityName() {
		return activityName;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public Short getActivityStatus() {
		return activityStatus;
	}

	public Double getActivityMoney() {
		return activityMoney;
	}
}
