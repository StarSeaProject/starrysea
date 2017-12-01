package top.starrysea.object.view.out;

public class ActivityForAll {

	private Integer activityId;
	private String activityName;

	public ActivityForAll(Integer activityId, String activityName) {
		this.activityId = activityId;
		this.activityName = activityName;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public String getActivityName() {
		return activityName;
	}
}
