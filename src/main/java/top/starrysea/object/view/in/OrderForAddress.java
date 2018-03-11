package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.Orders;

public class OrderForAddress {
	@NotEmpty(message = "订单号不能为空")
	private String orderNum;
	@NotNull(message = "地区Id不能为空")
	private Integer orderArea;
	@NotEmpty(message = "收货地址不能为空")
	@Length(max = 50, message = "收货地址长度不能超过50")
	private String orderAddress;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getOrderArea() {
		return orderArea;
	}

	public void setOrderArea(Integer orderArea) {
		this.orderArea = orderArea;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Orders toDTO() {
		return new Orders.Builder().orderNum(orderNum).orderArea(new Area.Builder().areaId(orderArea).build())
				.orderAddress(orderAddress).build();
	}
}
