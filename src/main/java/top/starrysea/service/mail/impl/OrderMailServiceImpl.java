package top.starrysea.service.mail.impl;

import org.springframework.stereotype.Service;

import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Orders;

@Service("orderMailService")
public class OrderMailServiceImpl extends MailServiceImpl {

	@Override
	public void sendMailService(Entity entity) {
		Orders order = (Orders) entity;
		String content = "<div>" + "这是一封确认邮件</div>" + "<div>您已经下单星之海的作品:" + order.getWork().getWorkName() + "</div>"
				+ "<div>您的订单号为：" + order.getOrderNum() + "</div>" + "<div>您可以根据此订单号查询您的发货信息</div>"
				+ "<div>我们会在发货后再发一封邮件来通知您</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}

}
