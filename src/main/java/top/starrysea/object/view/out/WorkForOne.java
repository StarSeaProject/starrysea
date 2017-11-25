package top.starrysea.object.view.out;

public class WorkForOne {
	private String workName;
	private String workUploadTime;
	private String workPdfpath;

	public WorkForOne(String workName, String workUploadTime, String workPdfpath) {
		super();
		this.workName = workName;
		this.workUploadTime = workUploadTime;
		this.workPdfpath = workPdfpath;
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

}
