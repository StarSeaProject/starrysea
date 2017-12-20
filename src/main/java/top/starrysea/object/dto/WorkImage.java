package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;

public class WorkImage implements Entity {

	private Integer workImageId;
	private Work work;
	private String workImagePath;

	private WorkImage(Builder builder) {
		this.workImageId = builder.workImageId;
		this.work = builder.work;
		this.workImagePath = builder.workImagePath;
	}

	public static class Builder implements IBuilder<WorkImage> {

		private Integer workImageId;
		private Work work;
		private String workImagePath;

		public Builder workImageId(Integer workImageId) {
			this.workImageId = workImageId;
			return this;
		}

		public Builder work(Work work) {
			this.work = work;
			return this;
		}

		public Builder workImagePath(String workImagePath) {
			this.workImagePath = workImagePath;
			return this;
		}

		@Override
		public WorkImage build() {
			return new WorkImage(this);
		}
	}

	public Integer getWorkImageId() {
		return workImageId;
	}

	public void setWorkImageId(Integer workImageId) {
		this.workImageId = workImageId;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getWorkImagePath() {
		return workImagePath;
	}

	public void setWorkImagePath(String workImagePath) {
		this.workImagePath = workImagePath;
	}

}
