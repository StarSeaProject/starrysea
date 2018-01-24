package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

public class WorkTypeForToAddOrder {

	@NotNull(message = "作品id不能为空")
	private Integer workId;
	@NotNull(message = "作品类型id不能为空")
	private Integer workTypeId;

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}

}
