package top.starrysea.service.mail.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import top.starrysea.common.Common;
import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.view.out.ModifyAddressForEmail;
import top.starrysea.security.SecurityAlgorithm;

@Service("modifyOrderMailService")
public class ModifyOrderMailServiceImpl extends MailServiceImpl {
	@Resource(name = "desede")
	private SecurityAlgorithm desede;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void sendMailService(Entity entity) {
		Orders order = (Orders) entity;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 3);
		String key;
		try {
			key = URLEncoder.encode(
					desede.encrypt(Common.toJson(new ModifyAddressForEmail(order.getOrderArea().getAreaName(),
							order.getOrderAddress(), calendar.getTime().getTime()))).replaceAll("\r|\n", "").trim(),
					"UTF-8");
			String content = MessageFormat.format(contentTemplate, order.getOrderNum(), order.getOrderNum(), key);
			mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿者公会", content));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected String getHtml() {
		return Common.readEmailHtml("modify_order_mail.html");
	}
}
