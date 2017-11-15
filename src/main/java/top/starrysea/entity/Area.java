package top.starrysea.entity;

public class Area extends Entity {

	private Integer areaId;
	private City city;
	private String areaName;

	public Area() {
	}

	public Area(Integer area, City city, String areaName) {
		this.areaId = area;
		this.city = city;
		this.areaName = areaName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
