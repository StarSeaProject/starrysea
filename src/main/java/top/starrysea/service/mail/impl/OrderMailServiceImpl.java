package top.starrysea.service.mail.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.dao.IWorkDao;
import top.starrysea.dao.IWorkTypeDao;
import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

@Service("orderMailService")
public class OrderMailServiceImpl extends MailServiceImpl {

	@Autowired
	private IWorkDao workDao;
	@Autowired
	private IWorkTypeDao workTypeDao;

	@Override
	public void sendMailService(List<? extends Entity> entitys) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Entity entity : entitys) {
			OrderDetail orderDetail = (OrderDetail) entity;
			WorkType wt = workTypeDao.getWorkTypeNameDao(orderDetail.getWorkType()).getResult(WorkType.class);
			wt.setWork(workDao.getWorkDao(orderDetail.getWorkType().getWork()).getResult(Work.class));
			orderDetail.setWorkType(wt);
			orderDetails.add(orderDetail);
		}
		Orders order = orderDetails.get(0).getOrder();
		StringBuilder orderDetailsStr = new StringBuilder(
				"<table border=1><thead><tr><td>作品名称</td><td>作品类型</td></tr></thead><tbody>");
		for (OrderDetail orderDetail : orderDetails) {
			orderDetailsStr.append("<tr>");
			orderDetailsStr.append("<td>" + orderDetail.getWorkType().getWork().getWorkName() + "</td>");
			orderDetailsStr.append("<td>" + orderDetail.getWorkType().getName() + "</td>");
			orderDetailsStr.append("</tr>");
		}
		orderDetailsStr.append("</tbody>");
		orderDetailsStr.append("</table>");
		String content = "<div>" + "这是一封确认邮件</div>" + "<div>您已经下单星之海的作品,以下是您下单的详细信息:</div>" + orderDetailsStr.toString()
				+ "<div>您的订单号为：<b>" + order.getOrderNum() + "</b></div>"
				+ "<div>您可以根据此订单号<a href=http://www.starrysea.top/order/" + order.getOrderNum() + ">查询您的发货信息</a></div>"
				+ "<div>我们会在发货后再发一封邮件来通知您</div>" + "<div>如有问题请联系我们的官博或者邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
	}

}
