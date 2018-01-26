package top.starrysea.object.view.out;

import java.util.Map;

public class ProvinceForAddOrder {

	private Integer provinceId;
	private String provinceName;
	private Map<Integer,CityForAddOrder> citys;

	public ProvinceForAddOrder(Integer provinceId, String provinceName) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public void setCitys(Map<Integer,CityForAddOrder> citys) {
		this.citys = citys;
	}

	public Map<Integer,CityForAddOrder> getCitys() {
		return citys;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

}
