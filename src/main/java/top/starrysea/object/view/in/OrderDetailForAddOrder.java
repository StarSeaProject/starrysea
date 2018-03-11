package top.starrysea.object.view.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

public class OrderDetailForAddOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4023866818064234016L;
	@NotNull(message = "作品Id不能为空")
	private Integer workId;
	@NotNull(message = "作品类型不能为空")
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
