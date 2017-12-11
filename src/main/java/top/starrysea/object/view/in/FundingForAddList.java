package top.starrysea.object.view.in;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FundingForAddList {

	@Valid
	private List<FundingForAdd> fundings;
	@NotNull(message = "活动id不能为空")
	private Integer activityId;

	public List<FundingForAdd> getFundings() {
		return fundings;
	}

	public void setFundings(List<FundingForAdd> fundings) {
		this.fundings = fundings;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	
}
