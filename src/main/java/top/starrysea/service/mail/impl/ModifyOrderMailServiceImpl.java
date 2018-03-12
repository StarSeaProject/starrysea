package top.starrysea.service.mail.impl;

import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import top.starrysea.common.Common;
import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Orders;

@Service("modifyOrderMailService")
public class ModifyOrderMailServiceImpl extends MailServiceImpl {

	@Override
	public void sendMailService(Entity entity) {
		Orders order = (Orders) entity;
		String content = MessageFormat.format(contentTemplate, order.getOrderNum(), order.getOrderNum(),
				Common.md5(order.getOrderArea().getAreaName() + order.getOrderAddress()));
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}

	@Override
	protected String getHtml() {
		return Common.readEmailHtml("modify_order_mail.html");
	}
}
