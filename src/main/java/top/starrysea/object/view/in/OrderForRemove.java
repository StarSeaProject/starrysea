package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.Orders;

public class OrderForRemove {

	@NotNull(message = "订单id不能为空")
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Orders toDTO() {
		return new Orders.Builder().orderId(orderId).build();
	}
}
