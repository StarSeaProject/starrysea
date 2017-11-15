package top.starrysea.entity;

public class Area extends Entity {
	private Integer areaId;
	private City cityId;
	private String areaName;
	
	public Area() {
	}

	public Area(Integer areaId, City cityId, String areaName) {
		this.areaId = areaId;
		this.cityId = cityId;
		this.areaName = areaName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
