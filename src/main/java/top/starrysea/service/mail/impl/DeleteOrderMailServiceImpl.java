package top.starrysea.service.mail.impl;

import org.springframework.stereotype.Service;

import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Orders;

@Service("deleteOrderMailService")
public class DeleteOrderMailServiceImpl extends MailServiceImpl {

	@Override
	public void sendMailService(Entity entity) {
		Orders order = (Orders) entity;
		String content = "<div>我们很遗憾地告诉您,您的订单<b>" + order.getOrderNum() + "</b>被管理员取消了</div>"
				+ "<div>您可以在官网上继续浏览并下单其他应援物</div>" + "<div>星之海感谢您的支持</div>"
				+ "<div>如有问题请联系我们的官博或者邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}
}
