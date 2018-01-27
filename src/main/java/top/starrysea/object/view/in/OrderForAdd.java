package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

public class OrderForAdd {
	
	@NotNull(message = "作品类型Id不能为空")
	private Integer workTypeId;
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
	@NotEmpty(message = "收件人邮箱不能为空")
	@Email(message = "输入的邮箱地址不是合法的")
	private String orderEmail;
	private String orderRemark;
	@NotEmpty(message = "收货人手机不能为空")
	@Length(max = 15, message = "收货人手机长度不能超过15")
	private String orderPhone;
	@NotEmpty(message="token序列不能为空!这是二次提交!")
	private String token;

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
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

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Orders toDTO() {
		return new Orders.Builder()
				.workType(new WorkType.Builder().workTypeId(workTypeId).work(new Work.Builder().workId(workId).build())
						.build())
				.orderName(orderName).orderArea(new Area.Builder().areaId(orderArea).build()).orderAddress(orderAddress)
				.orderEMail(orderEmail).orderRemark(orderRemark).orderPhone(orderPhone).build();
	}
}
