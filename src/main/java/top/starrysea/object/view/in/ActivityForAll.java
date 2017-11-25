package top.starrysea.object.view.in;

import top.starrysea.object.dto.Activity;

public class ActivityForAll {

	private String activityName;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityName(activityName).build();
	}
}
