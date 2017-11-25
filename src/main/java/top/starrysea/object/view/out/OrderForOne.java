package top.starrysea.object.view.out;

import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.Work;

public class OrderForOne {
	private String workName;
	private String orderName;
	private String province;
	private String city;
	private String area;
	private String orderAddress;
	private short orderStatus;
	private String orderExpressnum;
	private long orderTime;

	public OrderForOne(String workName, String orderName, String province, String city, String area,
			String orderAddress, short orderStatus, String orderExpressnum, long orderTime) {
		super();
		this.workName = workName;
		this.orderName = orderName;
		this.province = province;
		this.city = city;
		this.area = area;
		this.orderAddress = orderAddress;
		this.orderStatus = orderStatus;
		this.orderExpressnum = orderExpressnum;
		this.orderTime = orderTime;
	}

	public String getWorkName() {
		return workName;
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

	public short getOrderStatus() {
		return orderStatus;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public long getOrderTime() {
		return orderTime;
	}

}
