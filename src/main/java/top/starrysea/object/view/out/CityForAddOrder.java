package top.starrysea.object.view.out;

import java.util.List;

public class CityForAddOrder {

	private Integer cityId;
	private String cityName;
	private List<AreaForAddOrder> areas;

	public CityForAddOrder(Integer cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public List<AreaForAddOrder> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaForAddOrder> areas) {
		this.areas = areas;
	}
}
