package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;
import top.starrysea.object.view.out.WorkTypeForCar;

public class WorkType implements Entity {

	private Integer workTypeId;
	private Work work;
	private String name;
	private Integer stock;

	private WorkType(Builder builder) {
		this.workTypeId = builder.workTypeId;
		this.work = builder.work;
		this.name = builder.name;
		this.stock = builder.stock;
	}

	public static class Builder implements IBuilder<WorkType> {

		private Integer workTypeId;
		private Work work;
		private String name;
		private Integer stock;

		public Builder workTypeId(Integer workTypeId) {
			this.workTypeId = workTypeId;
			return this;
		}

		public Builder work(Work work) {
			this.work = work;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder stock(Integer stock) {
			this.stock = stock;
			return this;
		}

		@Override
		public WorkType build() {
			return new WorkType(this);
		}

	}

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public WorkTypeForCar toVoForCar() {
		return new WorkTypeForCar(work.getWorkCover(), work.getWorkName(), name);
	}
}
