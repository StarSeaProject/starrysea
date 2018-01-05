package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;

public class OrderForAdd {
	@NotNull(message = "作品Id不能为空")
	private Integer workId;
	@NotEmpty(message = "收货人姓名不能为空")
	@Length(max = 10, message = "姓名长度不能超过10")
	private String orderName;
	@NotNull(message = "地区Id不能为空")
	private Integer orderArea;
	@NotEmpty(message = "收货地址不能为空")
	@Length(max = 50, message = "收货地址长度不能超过50")
	private String orderAddress;
	@NotNull(message = "收件人邮箱不能为空")
	@Email(message = "输入的邮箱地址不是合法的")
	private String orderEmail;

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
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

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public Orders toDTO() {
		return new Orders.Builder().work(new Work.Builder().workId(workId).build()).orderName(orderName)
				.orderArea(new Area.Builder().areaId(orderArea).build()).orderAddress(orderAddress)
				.orderEMail(orderEmail).build();
	}
}
