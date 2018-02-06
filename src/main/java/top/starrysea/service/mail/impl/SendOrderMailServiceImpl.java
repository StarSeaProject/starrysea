package top.starrysea.service.mail.impl;

import org.springframework.stereotype.Service;

import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Orders;

@Service("sendOrderMailService")
public class SendOrderMailServiceImpl extends MailServiceImpl {

	@Override
	public void sendMailService(Entity entity) {
		Orders order = (Orders) entity;
		String content = "<div>这是一封通知发货的邮件</div>" + "<div>您订阅的星之海作品已经发货</div>" + "<div>您的快递单号为"
				+ order.getOrderExpressnum() + ",我们默认统一使用顺丰到付</div>" + "<div>您也可以使用订单号" + order.getOrderNum()
				+ "在官网<a href=http://www.starrysea.top/order/" + order.getOrderNum()
				+ ">查询快递单号</a>,但物流的详细信息需要您去顺丰官网查询</div>" + "<div>感谢您的订阅</div>"
				+ "<div>如有问题请联系我们的官博或者邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}

}
