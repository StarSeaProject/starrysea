package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

public class Admin implements Entity {

	private Integer adminId;
	private String adminUseraccount;
	private String adminPassword;

	public Admin(Builder builder) {
		this.adminId = builder.adminId;
		this.adminUseraccount = builder.adminUseraccount;
		this.adminPassword = builder.adminPassword;
	}

	public static class Builder implements IBuilder<Admin> {
		private Integer adminId;
		private String adminUseraccount;
		private String adminPassword;

		public Builder adminId(Integer adminId) {
			this.adminId = adminId;
			return this;
		}

		public Builder adminUseraccount(String adminUseraccount) {
			this.adminUseraccount = adminUseraccount;
			return this;
		}

		public Builder adminPassword(String adminPassword) {
			this.adminPassword = adminPassword;
			return this;
		}

		@Override
		public Admin build() {
			return new Admin(this);
		}

	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminUseraccount() {
		return adminUseraccount;
	}

	public void setAdminUseraccount(String adminUseraccount) {
		this.adminUseraccount = adminUseraccount;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}
