package top.starrysea.service.mail.impl;

import org.springframework.stereotype.Service;

import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Orders;

@Service("deleteOrderMailService")
public class DeleteOrderMailServiceImpl extends MailServiceImpl{

	@Override
	public void sendMailService(Entity entity) {
		Orders order=(Orders) entity;
		String content="";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}
}
