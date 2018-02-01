package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

public class OrderDetailForAddOrder {

	@NotNull(message = "作品Id不能为空")
	private Integer workId;
	@NotEmpty(message = "作品类型不能为空")
	private Integer workTypeId;

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public OrderDetail toDTO() {
		return new OrderDetail.Builder().workType(
				new WorkType.Builder().workTypeId(workTypeId).work(new Work.Builder().workId(workId).build()).build())
				.build();
	}
}
