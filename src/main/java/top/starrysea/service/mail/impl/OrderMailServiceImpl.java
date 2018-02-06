package top.starrysea.service.mail.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.Orders;

@Service("orderMailService")
public class OrderMailServiceImpl extends MailServiceImpl {

	@Override
	public void sendMailService(List<? extends Entity> entitys) {
		Orders order = ((OrderDetail) (entitys.get(0))).getOrder();
		StringBuilder orderDetails = new StringBuilder(
				"<table><thead><tr><td>作品名称</td><td>作品类型</td></tr></thead><tbody>");
		for (Entity entity : entitys) {
			OrderDetail orderDetail = (OrderDetail) entity;
			orderDetails.append("<tr>");
			orderDetails.append("<td>" + orderDetail.getWorkType().getWork().getWorkName() + "</td>");
			orderDetails.append("<td>" + orderDetail.getWorkType().getName() + "</td>");
			orderDetails.append("</tr>");
		}
		orderDetails.append("</tbody>");
		orderDetails.append("</table>");
		String content = "<div>" + "这是一封确认邮件</div>" + "<div>您已经下单星之海的作品,以下是您下单的详细信息:</div>" + orderDetails.toString()
				+ "<div>您的订单号为：<b>" + order.getOrderNum() + "</b></div>"
				+ "<div>您可以根据此订单号<a href=http://www.starrysea.top/order/" + order.getOrderNum() + ">查询您的发货信息</a></div>"
				+ "<div>我们会在发货后再发一封邮件来通知您</div>" + "<div>如有问题请联系我们的官博或者邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}

}
