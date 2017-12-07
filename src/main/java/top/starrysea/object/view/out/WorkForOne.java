package top.starrysea.object.view.out;

public class WorkForOne {
	private String workName;
	private String workUploadTime;
	private String workPdfpath;
	private Integer workClick;

	public WorkForOne(String workName, String workUploadTime, String workPdfpath, Integer workClick) {
		this.workName = workName;
		this.workUploadTime = workUploadTime;
		this.workPdfpath = workPdfpath;
		this.workClick = workClick;
	}

	public String getWorkName() {
		return workName;
	}

	public String getWorkUploadTime() {
		return workUploadTime;
	}

	public String getWorkPdfpath() {
		return workPdfpath;
	}

	public Integer getWorkClick() {
		return workClick;
	}

}
