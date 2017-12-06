package top.starrysea.object.view.in;

import top.starrysea.common.Condition;
import top.starrysea.object.dto.Work;

public class WorkForAll {

	private String workName;
	private Integer page;

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Condition getCondition() {
		return new Condition(page != null ? page : 1);
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Work toDTO() {
		return new Work.Builder().workName(workName).build();
	}
}
