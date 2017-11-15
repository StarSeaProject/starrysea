package top.starrysea.entity;

public class City extends Entity {
	private Integer cityId;
	private Province provinceId;
	private String cityName;
	
	public City() {
	}

	public City(Integer cityId, Province provinceId, String cityName) {
		this.cityId = cityId;
		this.provinceId = provinceId;
		this.cityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Province getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Province provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
}
