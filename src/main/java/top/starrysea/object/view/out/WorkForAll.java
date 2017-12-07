package top.starrysea.object.view.out;

public class WorkForAll {
	private Integer workId;
	private String workName;
	private String workCover;
	private String workSummary;

	public WorkForAll(Integer workId, String workName, String workCover, String workSummary) {
		this.workId = workId;
		this.workName = workName;
		this.workCover = workCover;
		this.workSummary = workSummary;
	}

	public Integer getWorkId() {
		return workId;
	}

	public String getWorkName() {
		return workName;
	}

	public String getWorkCover() {
		return workCover;
	}

	public String getWorkSummary() {
		return workSummary;
	}

}
