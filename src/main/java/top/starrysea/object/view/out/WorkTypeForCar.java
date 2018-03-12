package top.starrysea.object.view.out;

public class WorkTypeForCar {

	private String workCover;
	private String workName;
	private String workTypeName;
	private Integer stock;

	public WorkTypeForCar(String workCover, String workName, String workTypeName, Integer stock) {
		this.workCover = workCover;
		this.workName = workName;
		this.workTypeName = workTypeName;
		this.stock = stock;
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

	public Integer getStock() {
		return stock;
	}

}
