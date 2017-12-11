package top.starrysea.object.view.out;

public class ActivityForAll {

	private Integer activityId;
	private String activityName;
	private String activityCover;
	private String activitySummary;

	public ActivityForAll(Integer activityId, String activityName, String activityCover, String activitySummary) {
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityCover = activityCover;
		this.activitySummary = activitySummary;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public String getActivityCover() {
		return activityCover;
	}

	public String getActivitySummary() {
		return activitySummary;
	}
}
