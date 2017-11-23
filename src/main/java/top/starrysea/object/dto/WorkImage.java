package top.starrysea.object.dto;

public class WorkImage extends Entity {
	
	private Integer workImageId;
	private Work work;
	private String workImagePath;

	public WorkImage() {
	}

	public WorkImage(Integer workImageId, Work work, String workImagePath) {
		this.workImageId = workImageId;
		this.work = work;
		this.workImagePath = workImagePath;
	}

	public Integer getWorkImageId() {
		return workImageId;
	}

	public void setWorkImageId(Integer workImageId) {
		this.workImageId = workImageId;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getWorkImagePath() {
		return workImagePath;
	}

	public void setWorkImagePath(String workImagePath) {
		this.workImagePath = workImagePath;
	}

}
