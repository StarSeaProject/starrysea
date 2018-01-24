package top.starrysea.object.view.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.WorkType;

public class WorkTypeForModify {

	@NotNull(message = "作品类型id不能为空")
	private Integer workTypeId;
	@NotNull(message = "作品库存不能为空")
	@Min(value = 0, message = "作品库存不能为负数")
	private Integer stock;

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}

	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public WorkType toDTO() {
		return new WorkType.Builder().workTypeId(workTypeId).stock(stock).build();
	}
}
