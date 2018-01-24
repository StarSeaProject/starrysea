package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.WorkType;

public class WorkTypeForRemove {

	@NotNull(message = "作品类型id不能为空")
	private Integer workTypeId;

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}

	public WorkType toDTO() {
		return new WorkType.Builder().workTypeId(workTypeId).build();
	}
}
