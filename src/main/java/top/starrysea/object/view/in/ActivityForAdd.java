package top.starrysea.object.view.in;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Activity;

public class ActivityForAdd {

	@NotEmpty(message = "活动名称不能为空")
	@Length(max = 30, message = "活动名称长度不能超过30")
	private String activityName;
	@NotEmpty(message = "活动内容不能为空")
	private String activityContent;

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

	public Activity toDTO() {
		return new Activity.Builder().activityName(activityName).activityContent(activityContent).build();
	}

}
