package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.Work;

public class WorkForModify {
	@NotNull(message = "作品ID不能为空")
	private Integer workId;

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public Work toDTO() {
		return new Work.Builder().workId(workId).build();
	}
}
