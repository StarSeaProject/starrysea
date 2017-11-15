package top.starrysea.entity;

public class WorkImage extends Entity {
	private Integer workImageId;
	private Work workId;
	private String workImagePath;
	public WorkImage() {
	}
	public WorkImage(Integer workImageId, Work workId, String workImagePath) {
		this.workImageId = workImageId;
		this.workId = workId;
		this.workImagePath = workImagePath;
	}
	public Integer getWorkImageId() {
		return workImageId;
	}
	public void setWorkImageId(Integer workImageId) {
		this.workImageId = workImageId;
	}
	public Work getWorkId() {
		return workId;
	}
	public void setWorkId(Work workId) {
		this.workId = workId;
	}
	public String getWorkImagePath() {
		return workImagePath;
	}
	public void setWorkImagePath(String workImagePath) {
		this.workImagePath = workImagePath;
	}
	
}
