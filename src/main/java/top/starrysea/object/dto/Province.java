package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;

public class Province implements Entity {

	private Integer provinceId;
	private String provinceName;

	public Province(Integer provinceId, String provinceName) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
