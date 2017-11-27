package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import top.starrysea.object.dto.Online;

public class OnlineForAdd {

	@NotNull(message="订阅邮箱不能为空")
	@Email(message="输入的邮箱地址不是合法的")
	private String onlineEmail;

	public String getOnlineEmail() {
		return onlineEmail;
	}

	public void setOnlineEmail(String onlineEmail) {
		this.onlineEmail = onlineEmail;
	}
	public Online toDTO() {
		return new Online.Builder().onlineEmail(onlineEmail).build();
	}
}
