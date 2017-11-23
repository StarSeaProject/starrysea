package top.starrysea.object.view.in;

import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Activity;

public class ActivityForModify {

	@NotEmpty(message="活动id不能为空")
	private Integer activityId;
	@NotEmpty(message="活动状态不能为空")
	private short activityStatus;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public short getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(short activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityId(activityId).activityStatus(activityStatus).build();
	}
}
