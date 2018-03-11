package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.Orders;

public class OrderDetailForModifyAddr {
	@NotNull(message = "订单号不能为空")
	private String orderNum;
	@NotNull(message = "校验码不能为空")
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Orders toDTO() {
		return new Orders.Builder().orderNum(orderNum).build();
	}
}
