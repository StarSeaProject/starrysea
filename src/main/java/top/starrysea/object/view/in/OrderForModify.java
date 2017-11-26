package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Orders;

public class OrderForModify {
	@NotNull(message = "订单id不能为空")
	private String orderId;
	@NotEmpty(message = "订单状态不能为空")
	private short orderStatus;
	private String orderExpressnum;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public short getOrderStatus() {
		return orderStatus;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public Orders toDTO() {
		if (orderExpressnum == null)
			return new Orders.Builder().orderId(orderId).orderStatus(orderStatus).build();
		else
			return new Orders.Builder().orderId(orderId).orderStatus(orderStatus).orderExpressnum(orderExpressnum)
					.build();
	}
}
