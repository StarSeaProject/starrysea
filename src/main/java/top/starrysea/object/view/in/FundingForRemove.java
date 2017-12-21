package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.Funding;

public class FundingForRemove {

	@NotNull(message = "众筹记录id不能为空")
	private Integer fundingId;
	@NotNull(message = "众筹金额不能为空")
	private Double fundingMoney;
	@NotNull(message = "活动id不能为空")
	private Integer activityId;

	public Integer getFundingId() {
		return fundingId;
	}

	public void setFundingId(Integer fundingId) {
		this.fundingId = fundingId;
	}

	public Double getFundingMoney() {
		return fundingMoney;
	}

	public void setFundingMoney(Double fundingMoney) {
		this.fundingMoney = fundingMoney;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Funding toDTO() {
		return new Funding.Builder().fundingId(fundingId).fundingMoney(fundingMoney)
				.activity(new Activity.Builder().activityId(activityId).build()).build();
	}
}
