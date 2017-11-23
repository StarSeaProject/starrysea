package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.Activity;

public class ActivityForModify {

	@NotNull(message="活动id不能为空")
	private Integer activityId;
	@NotNull(message="活动状态不能为空")
	private Short activityStatus;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Short getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Short activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityId(activityId).activityStatus(activityStatus).build();
	}
}
