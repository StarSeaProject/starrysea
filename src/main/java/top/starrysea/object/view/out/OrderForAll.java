package top.starrysea.object.view.out;

public class OrderForAll {
	private String orderId;
	private String orderNum;
	private String orderName;
	private short orderStatus;
	private long orderTime;

	public OrderForAll(String orderId, String orderNum, String orderName, short orderStatus, long orderTime) {
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

	public short getOrderStatus() {
		return orderStatus;
	}

	public long getOrderTime() {
		return orderTime;
	}

}
