package top.starrysea.object.view.in;

import top.starrysea.object.dto.WorkImage;

public class WorkImageForAdd {

	private String workImagePath;

	public String getWorkImagePath() {
		return workImagePath;
	}

	public void setWorkImagePath(String workImagePath) {
		this.workImagePath = workImagePath;
	}
	
	public WorkImage toDTO() {
		return new WorkImage.Builder().workImagePath(workImagePath).build();
	}
}
