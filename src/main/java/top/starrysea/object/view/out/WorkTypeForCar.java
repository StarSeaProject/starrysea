package top.starrysea.object.view.out;

public class WorkTypeForCar {

	private String workCover;
	private String workName;
	private String workTypeName;

	public WorkTypeForCar(String workCover, String workName, String workTypeName) {
		this.workCover = workCover;
		this.workName = workName;
		this.workTypeName = workTypeName;
	}

	public String getWorkCover() {
		return workCover;
	}

	public String getWorkName() {
		return workName;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

}
