package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.object.dto.Funding;

public class FundingForRemove {

	@NotNull(message="众筹记录id不能为空")
	private Integer fundingId;

	public Integer getFundingId() {
		return fundingId;
	}
	
	public void setFundingId(Integer fundingId) {
		this.fundingId = fundingId;
	}

	public Funding toDTO() {
		return new Funding.Builder().fundingId(fundingId).build();
	}
}
