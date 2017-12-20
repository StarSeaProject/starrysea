package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.object.view.out.ActivityForAll;
import top.starrysea.object.view.out.ActivityForOne;

public class Activity implements Entity {

	private Integer activityId;
	private String activityName;
	private String activityContent;
	private short activityStatus;
	private String activityCover;
	private String activitySummary;
	private String activityEndtime;

	public Activity(Builder builder) {
		this.activityId = builder.activityId;
		this.activityName = builder.activityName;
		this.activityContent = builder.activityContent;
		this.activityStatus = builder.activityStatus;
		this.activityCover = builder.activityCover;
		this.activitySummary = builder.activitySummary;
		this.activityEndtime = builder.activityEndtime;
	}

	public static class Builder implements IBuilder<Activity> {
		private Integer activityId;
		private String activityName;
		private String activityContent;
		private short activityStatus;
		private String activityCover;
		private String activitySummary;
		private String activityEndtime;

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

		public Builder activityCover(String activityCover) {
			this.activityCover = activityCover;
			return this;
		}

		public Builder activitySummary(String activitySummary) {
			this.activitySummary = activitySummary;
			return this;
		}

		public Builder activityEndtime(String activityEndtime) {
			this.activityEndtime = activityEndtime;
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

	public String getActivityCover() {
		return activityCover;
	}

	public void setActivityCover(String activityCover) {
		this.activityCover = activityCover;
	}

	public String getActivitySummary() {
		return activitySummary;
	}

	public void setActivitySummary(String activitySummary) {
		this.activitySummary = activitySummary;
	}

	public String getActivityEndtime() {
		return activityEndtime;
	}

	public void setActivityEndtime(String activityEndtime) {
		this.activityEndtime = activityEndtime;
	}

	public ActivityForAll toVoForAll() {
		return new ActivityForAll(activityId, activityName, activityCover, activitySummary, activityEndtime);
	}

	public ActivityForOne toVoForOne() {
		return new ActivityForOne(activityName, activityContent, activityStatus);
	}
}
