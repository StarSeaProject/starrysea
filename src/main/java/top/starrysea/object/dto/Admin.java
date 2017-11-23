package top.starrysea.object.dto;

public class Admin extends Entity {

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

		public Builder() {
		}

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
			// TODO 自动生成的方法存根
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
