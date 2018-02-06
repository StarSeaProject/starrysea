package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;
import top.starrysea.object.view.out.OrderDetailForOne;

public class OrderDetail implements Entity {

	private String id;
	private WorkType workType;
	private Orders order;

	private OrderDetail(Builder builder) {
		this.id = builder.id;
		this.workType = builder.workType;
		this.order = builder.order;
	}

	public static class Builder implements IBuilder<OrderDetail> {
		private String id;
		private WorkType workType;
		private Orders order;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder workType(WorkType workType) {
			this.workType = workType;
			return this;
		}

		public Builder order(Orders order) {
			this.order = order;
			return this;
		}

		@Override
		public OrderDetail build() {
			return new OrderDetail(this);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public OrderDetailForOne toVoForOne() {
		return new OrderDetailForOne(this);
	}
}
