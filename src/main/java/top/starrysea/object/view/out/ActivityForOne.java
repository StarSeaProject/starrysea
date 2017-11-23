package top.starrysea.object.view.out;

public class ActivityForOne {

	private String activityName;
	private String activityContent;
	private short activityStatus;
	public ActivityForOne(String activityName, String activityContent, short activityStatus) {
		this.activityName = activityName;
		this.activityContent = activityContent;
		this.activityStatus = activityStatus;
	}
	public String getActivityName() {
		return activityName;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public short getActivityStatus() {
		return activityStatus;
	}
}
