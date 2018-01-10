package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

public class ActivityImage implements Entity{

	private Integer activityImageId;
	private Activity activity;
	private String activityImagePath;

	private ActivityImage(Builder builder) {
		this.activityImageId = builder.activityImageId;
		this.activity = builder.activity;
		this.activityImagePath = builder.activityImagePath;
	}

	public static class Builder implements IBuilder<ActivityImage> {

		private Integer activityImageId;
		private Activity activity;
		private String activityImagePath;

		public Builder activityImageId(Integer activityImageId) {
			this.activityImageId = activityImageId;
			return this;
		}

		public Builder activity(Activity activity) {
			this.activity = activity;
			return this;
		}

		public Builder activityImagePath(String activityImagePath) {
			this.activityImagePath = activityImagePath;
			return this;
		}

		@Override
		public ActivityImage build() {
			return new ActivityImage(this);
		}

	}

	public Integer getActivityImageId() {
		return activityImageId;
	}

	public Activity getActivity() {
		return activity;
	}

	public String getActivityImagePath() {
		return activityImagePath;
	}

	public void setActivityImageId(Integer activityImageId) {
		this.activityImageId = activityImageId;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setActivityImagePath(String activityImagePath) {
		this.activityImagePath = activityImagePath;
	}

}
