package top.starrysea.object.view.in;

import top.starrysea.object.dto.Orders;

public class OrderForAll {
	private String orderName;

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Orders toDTO() {
		return new Orders.Builder().orderName(orderName).build();
	}
}
