package top.starrysea.object.view.out;

import javax.validation.constraints.NotNull;

public class WorkTypeForRemoveCar {

	@NotNull(message="删除下标不能为空")
	private Integer index;
	@NotNull(message="不要重复提交")
	private String token;

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIndex() {
		return index;
	}

	public String getToken() {
		return token;
	}

}
