package top.starrysea.service.mail.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Orders;

@Service("orderMailService")
public class OrderMailServiceImpl extends MailServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void sendMailService(Entity entity) {
		Orders order = (Orders) entity;
		String content = "<div>" + "这是一封确认邮件</div>" + "<div>您已经下单星之海的作品:" + order.getWorkType().getWork().getWorkName()
				+ "(" + order.getWorkType().getName() + ")" + "</div>" + "<div>您的订单号为：<b>" + order.getOrderNum()
				+ "</b></div>" + "<div>您可以根据此订单号<a href=http://www.starrysea.top/order/" + order.getOrderNum()
				+ ">查询您的发货信息</a></div>" + "<div>我们会在发货后再发一封邮件来通知您</div>" + "<div>如有问题请联系我们的官博或者邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}

}
