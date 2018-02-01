package top.starrysea.object.view.in;

import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.WorkType;

public class OrderDetailForAddOrder {

	@NotEmpty(message = "作品类型不能为空")
	private Integer workTypeId;

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}

	public OrderDetail toDTO() {
		return new OrderDetail.Builder().workType(new WorkType.Builder().workTypeId(workTypeId).build()).build();
	}
}
