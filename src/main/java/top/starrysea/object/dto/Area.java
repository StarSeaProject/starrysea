package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;
import top.starrysea.object.view.out.AreaForAddOrder;

public class Area implements Entity {

	private Integer areaId;
	private City city;
	private String areaName;

	private Area(Builder builder) {
		this.areaId = builder.areaId;
		this.city = builder.city;
		this.areaName = builder.areaName;
	}

	public static class Builder implements IBuilder<Area> {
		private Integer areaId;
		private City city;
		private String areaName;

		public Builder areaId(Integer areaId) {
			this.areaId = areaId;
			return this;
		}

		public Builder city(City city) {
			this.city = city;
			return this;
		}

		public Builder areaName(String areaName) {
			this.areaName = areaName;
			return this;
		}

		@Override
		public Area build() {
			return new Area(this);
		}
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

	public AreaForAddOrder toVo() {
		return new AreaForAddOrder(areaId, areaName);
	}
}
