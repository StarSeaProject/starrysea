package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.Orders;

public class OrderForModify {
	@NotNull(message = "订单id不能为空")
	private String orderId;
	@NotNull(message = "订单状态不能为空")
	private Short orderStatus;
	private String orderExpressnum;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Short getOrderStatus() {
		return orderStatus;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setOrderExpressnum(String orderExpressnum) {
		this.orderExpressnum = orderExpressnum;
	}

	public Orders toDTO() {
		if (orderExpressnum == null)
			return new Orders.Builder().orderId(orderId).orderStatus(orderStatus).build();
		else
			return new Orders.Builder().orderId(orderId).orderStatus(orderStatus).orderExpressnum(orderExpressnum)
					.build();
	}
}
