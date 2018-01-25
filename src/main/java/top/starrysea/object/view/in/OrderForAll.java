package top.starrysea.object.view.in;

import top.starrysea.common.Condition;
import top.starrysea.object.dto.Orders;

public class OrderForAll {

	private String orderName;
	private Integer page;

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Condition getCondition() {
		return new Condition(page != null ? page : 1);
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Orders toDTO() {
		return new Orders.Builder().orderName(orderName).build();
	}
}
