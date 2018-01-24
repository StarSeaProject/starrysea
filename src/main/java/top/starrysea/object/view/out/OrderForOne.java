package top.starrysea.object.view.out;

import java.util.Date;

import top.starrysea.common.Common;
import top.starrysea.object.dto.Orders;

public class OrderForOne {
	private String workTypeName;
	private String orderName;
	private String province;
	private String city;
	private String area;
	private String orderAddress;
	private String orderStatus;
	private String orderExpressnum;
	private String orderTime;
	private String orderEMail;

	public OrderForOne(Orders order) {
		this.workTypeName = order.getWorkType().getName();
		this.orderName = order.getOrderName();
		this.province = order.getOrderArea().getCity().getProvince().getProvinceName();
		this.city = order.getOrderArea().getCity().getCityName();
		this.area = order.getOrderArea().getAreaName();
		this.orderAddress = order.getOrderAddress();
		String status = "";
		if (order.getOrderStatus() == (short) 1) {
			status = "未发货";
		} else if (order.getOrderStatus() == (short) 2) {
			status = "已发货";
		}
		this.orderStatus = status;
		this.orderExpressnum = order.getOrderExpressnum();
		this.orderTime = Common.time2String(new Date(order.getOrderTime()));
		this.orderEMail = order.getOrderEMail();
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public String getOrderName() {
		return orderName;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getArea() {
		return area;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public String getOrderEMail() {
		return orderEMail;
	}

}
