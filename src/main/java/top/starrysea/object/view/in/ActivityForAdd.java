package top.starrysea.object.view.in;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;

public class ActivityForAdd {

	@NotEmpty(message = "活动名称不能为空")
	@Length(max = 30, message = "活动名称长度不能超过30")
	private String activityName;
	@NotEmpty(message = "活动内容不能为空")
	private String activityContent;
	private List<ActivityImageForAdd> activityImages;
	@NotEmpty(message = "活动概要不能为空")
	private String activitySummary;

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

	public List<ActivityImageForAdd> getActivityImages() {
		return activityImages;
	}

	public void setActivityImages(List<ActivityImageForAdd> activityImages) {
		this.activityImages = activityImages;
	}

	public String getActivitySummary() {
		return activitySummary;
	}

	public void setActivitySummary(String activitySummary) {
		this.activitySummary = activitySummary;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityName(activityName).activityContent(activityContent).activitySummary(activitySummary).build();
	}

	public List<ActivityImage> toDTOImage() {
		List<ActivityImage> list = new ArrayList<>();
		if (activityImages == null || activityImages.isEmpty())
			return list;
		for (ActivityImageForAdd activityImage : activityImages) {
			ActivityImage ai = new ActivityImage.Builder().activityImagePath(activityImage.getActivityImagePath())
					.build();
			list.add(ai);
		}
		return list;
	}
}
