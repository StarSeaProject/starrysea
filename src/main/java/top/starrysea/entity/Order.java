package top.starrysea.entity;

public class Order extends Entity {
	private String orderId;
	private String orderNum;
	private String orderName;
	private Area orderArea;
	private String orderAddress;
	private short orderStatus;
	public Order() {
	}
	public Order(String orderId, String orderNum, String orderName, Area orderArea, String orderAddress,
			short orderStatus) {
		this.orderId = orderId;
		this.orderNum = orderNum;
		this.orderName = orderName;
		this.orderArea = orderArea;
		this.orderAddress = orderAddress;
		this.orderStatus = orderStatus;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Area getOrderArea() {
		return orderArea;
	}
	public void setOrderArea(Area orderArea) {
		this.orderArea = orderArea;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public short getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(short orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
