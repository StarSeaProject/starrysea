package top.starrysea.object.view.in;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderDetailForRemoveOrder {

	@NotEmpty(message="作品详细id不能为空")
	private String orderDetailId;

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
}
