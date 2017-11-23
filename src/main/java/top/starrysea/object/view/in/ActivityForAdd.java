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
	@NotEmpty(message = "二维码不能为空")
	@Length(max = 50, message = "二维码字符串长度不能超过50")
	private String activityQrcode;

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

	public String getActivityQrcode() {
		return activityQrcode;
	}

	public void setActivityQrcode(String activityQrcode) {
		this.activityQrcode = activityQrcode;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityName(activityName).activityContent(activityContent)
				.activityQrcode(activityQrcode).build();
	}

}
