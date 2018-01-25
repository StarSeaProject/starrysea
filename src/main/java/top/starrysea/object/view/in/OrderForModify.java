package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import top.starrysea.object.dto.Orders;

public class OrderForModify {
	@NotNull(message = "订单id不能为空")
	private String orderId;
	@NotNull(message = "快递单号不能为空")
	@Size(max = 30, message = "快递单号长度不能超过30")
	private String orderExpressnum;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public void setOrderExpressnum(String orderExpressnum) {
		this.orderExpressnum = orderExpressnum;
	}

	public Orders toDTO() {
		return new Orders.Builder().orderId(orderId).orderExpressnum(orderExpressnum).build();
	}
}
