package top.starrysea.entity;

public class City extends Entity {
	
	private Integer cityId;
	private Province province;
	private String cityName;
	
	public City() {
	}

	public City(Integer cityId, Province province, String cityName) {
		this.cityId = cityId;
		this.province = province;
		this.cityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Province getProvinceId() {
		return province;
	}

	public void setProvinceId(Province province) {
		this.province = province;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
