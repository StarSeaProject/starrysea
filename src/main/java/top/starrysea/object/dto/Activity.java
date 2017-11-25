package top.starrysea.object.dto;

import top.starrysea.object.view.out.ActivityForAll;
import top.starrysea.object.view.out.ActivityForOne;

public class Activity extends Entity {

	private Integer activityId;
	private String activityName;
	private String activityContent;
	private short activityStatus;
	private String activityQrcode;

	public Activity(Builder builder) {
		this.activityId = builder.activityId;
		this.activityName = builder.activityName;
		this.activityContent = builder.activityContent;
		this.activityStatus = builder.activityStatus;
		this.activityQrcode = builder.activityQrcode;
	}

	public static class Builder implements IBuilder<Activity> {
		private Integer activityId;
		private String activityName;
		private String activityContent;
		private short activityStatus;
		private String activityQrcode;

		public Builder() {
		}

		public Builder activityId(Integer activityId) {
			this.activityId = activityId;
			return this;
		}

		public Builder activityName(String activityName) {
			this.activityName = activityName;
			return this;
		}

		public Builder activityContent(String activityContent) {
			this.activityContent = activityContent;
			return this;
		}

		public Builder activityStatus(short activityStatus) {
			this.activityStatus = activityStatus;
			return this;
		}

		public Builder activityQrcode(String activityQrcode) {
			this.activityQrcode = activityQrcode;
			return this;
		}

		@Override
		public Activity build() {
			return new Activity(this);
		}

	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

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

	public short getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(short activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getActivityQcode() {
		return activityQrcode;
	}

	public void setActivityQcode(String activityQcode) {
		this.activityQrcode = activityQcode;
	}

	public ActivityForAll toVoForAll() {
		return new ActivityForAll(activityId, activityName);
	}
	
	public ActivityForOne toVoForOne() {
		return new ActivityForOne(activityName, activityContent, activityStatus);
	}
}
