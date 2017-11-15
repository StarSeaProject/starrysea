package top.starrysea.entity;

public class Work extends Entity{

	private Integer workId;
	private String workName;
	private String workUploadTime;
	private String workPdfpath;
	private Integer workStock;
	public Work() {
	}
	public Work(Integer workId, String workName, String workUploadTime, String workPdfpath, Integer workStock) {
		super();
		this.workId = workId;
		this.workName = workName;
		this.workUploadTime = workUploadTime;
		this.workPdfpath = workPdfpath;
		this.workStock = workStock;
	}
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getWorkUploadTime() {
		return workUploadTime;
	}
	public void setWorkUploadTime(String workUploadTime) {
		this.workUploadTime = workUploadTime;
	}
	public String getWorkPdfpath() {
		return workPdfpath;
	}
	public void setWorkPdfpath(String workPdfpath) {
		this.workPdfpath = workPdfpath;
	}
	public Integer getWorkStock() {
		return workStock;
	}
	public void setWorkStock(Integer workStock) {
		this.workStock = workStock;
	}
	
}
