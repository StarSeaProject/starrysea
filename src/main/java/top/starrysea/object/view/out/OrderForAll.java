package top.starrysea.object.view.out;

public class OrderForAll {
	private String orderId;
	private String orderNum;
	private String orderName;
	private String orderStatus;
	private long orderTime;

	public OrderForAll(String orderId, String orderNum, String orderName, String orderStatus, long orderTime) {
		super();
		this.orderId = orderId;
		this.orderNum = orderNum;
		this.orderName = orderName;
		this.orderStatus = orderStatus;
		this.orderTime = orderTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public String getOrderName() {
		return orderName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public long getOrderTime() {
		return orderTime;
	}

}
