package top.starrysea.object.view.out;

public class OrderForOne {
	private String workName;
	private String orderName;
	private String province;
	private String city;
	private String area;
	private String orderAddress;
	private String orderStatus;
	private String orderExpressnum;
	private String orderTime;

	public OrderForOne(String workName, String orderName, String province, String city, String area,
			String orderAddress, String orderStatus, String orderExpressnum, String orderTime) {
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public String getOrderTime() {
		return orderTime;
	}

}
