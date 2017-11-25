package top.starrysea.object.dto;

import top.starrysea.object.view.out.WorkForAll;
import top.starrysea.object.view.out.WorkForOne;

public class Work extends Entity {

	private Integer workId;
	private String workName;
	private String workUploadTime;
	private String workPdfpath;
	private Integer workStock;

	private Work(Builder builder) {
		this.workId = builder.workId;
		this.workName = builder.workName;
		this.workUploadTime = builder.workUploadTime;
		this.workPdfpath = builder.workPdfpath;
		this.workStock = builder.workStock;
	}

	public static class Builder implements IBuilder<Work> {

		private Integer workId;
		private String workName;
		private String workUploadTime;
		private String workPdfpath;
		private Integer workStock;

		public Builder() {
		}

		public Builder workId(Integer workId) {
			this.workId = workId;
			return this;
		}

		public Builder workName(String workName) {
			this.workName = workName;
			return this;
		}

		public Builder workUploadTime(String workUploadTime) {
			this.workUploadTime = workUploadTime;
			return this;
		}

		public Builder workPdfpath(String workPdfpath) {
			this.workPdfpath = workPdfpath;
			return this;
		}

		public Builder workStock(Integer workStock) {
			this.workStock = workStock;
			return this;
		}

		@Override
		public Work build() {
			return new Work(this);
		}

	}

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkUploadTime() {
		return workUploadTime;
	}

	public void setWorkUploadTime(String workUploadTime) {
		this.workUploadTime = workUploadTime;
	}

	public String getWorkPdfpath() {
		return workPdfpath;
	}

	public void setWorkPdfpath(String workPdfpath) {
		this.workPdfpath = workPdfpath;
	}

	public Integer getWorkStock() {
		return workStock;
	}

	public void setWorkStock(Integer workStock) {
		this.workStock = workStock;
	}
	public WorkForAll toVoForAll(){
		return new WorkForAll(workId, workName);
	}

	public Object toVoForOne() {
		return new WorkForOne(workName, workUploadTime, workPdfpath);
	}
}
