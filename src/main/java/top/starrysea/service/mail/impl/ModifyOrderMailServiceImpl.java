package top.starrysea.service.mail.impl;

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
		String content = "<div>您的订单：<b>" + order.getOrderNum() + "</b>正在修改收货地址</div>"
				+ "<div>请尽快根据此链接<a href=http://starrysea.top/order/address/toModifyAddr?orderNum=" + order.getOrderNum()
				+ "&key=" + Common.md5(order.getOrderArea().getAreaName() + order.getOrderAddress())
				+ ">修改收货地址</a></div>" + "<div>星之海感谢您的支持</div>" + "<div>如有问题请联系我们的官博或者邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}
}
