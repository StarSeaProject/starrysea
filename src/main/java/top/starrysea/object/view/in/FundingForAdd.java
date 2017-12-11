package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.Funding;

public class FundingForAdd {

	private Integer activityId;
	@NotEmpty(message = "众筹人姓名不能为空!")
	@Length(max = 20, message = "众筹人姓名长度不能超过20")
	private String fundingName;
	@NotNull(message = "众筹金额不能为空")
	private Double fundingMoney;
	@NotEmpty(message = "众筹人留言不能为空!")
	@Length(max = 50, message = "众筹人留言长度不能超过50")
	private String fundingMessage;

	public String getFundingName() {
		return fundingName;
	}

	public Double getFundingMoney() {
		return fundingMoney;
	}

	public String getFundingMessage() {
		return fundingMessage;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public void setFundingName(String fundingName) {
		this.fundingName = fundingName;
	}

	public void setFundingMoney(Double fundingMoney) {
		this.fundingMoney = fundingMoney;
	}

	public void setFundingMessage(String fundingMessage) {
		this.fundingMessage = fundingMessage;
	}

	public Funding toDTO() {
		return new Funding.Builder().activity(new Activity.Builder().activityId(activityId).build())
				.fundingName(fundingName).fundingMoney(fundingMoney).fundingMessage(fundingMessage).build();
	}
}
