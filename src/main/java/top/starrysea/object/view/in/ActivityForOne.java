package top.starrysea.object.view.in;

import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Activity;

public class ActivityForOne {

	@NotEmpty(message = "活动id不能为空")
	private Integer activityId;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityId(activityId).build();
	}
}
