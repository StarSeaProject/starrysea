package top.starrysea.object.view.out;

public class ActivityForOne {

	private String activityName;
	private String activityContent;
	private String activityStatus;

	public ActivityForOne(String activityName, String activityContent, Short activityStatus) {
		this.activityName = activityName;
		this.activityContent = activityContent;
		switch (activityStatus) {
		case 1:
			this.activityStatus = "未开始";
			break;
		case 2:
			this.activityStatus = "进行中";
			break;
		case 3:
			this.activityStatus = "已结束";
			break;
		}
	}

	public String getActivityName() {
		return activityName;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public String getActivityStatus() {
		return activityStatus;
	}
}
