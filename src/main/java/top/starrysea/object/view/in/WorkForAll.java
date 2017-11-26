package top.starrysea.object.view.in;

import top.starrysea.object.dto.Work;

public class WorkForAll {
	private String workName;

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Work toDTO() {
		return new Work.Builder().workName(workName).build();
	}
}
