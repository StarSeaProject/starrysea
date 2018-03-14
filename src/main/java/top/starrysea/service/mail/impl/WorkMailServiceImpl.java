package top.starrysea.service.mail.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IOnlineDao;
import top.starrysea.kql.entity.Entity;
import top.starrysea.mail.Mail;
import top.starrysea.object.dto.Online;
import top.starrysea.object.dto.Work;

//这是用于作品新增时推送的邮件服务
@Service("workMailService")
public class WorkMailServiceImpl extends MailServiceImpl {
	
	@Autowired
	private IOnlineDao onlineDao;

	@Override
	public void sendMailService(Entity entity) {
		DaoResult daoResult = onlineDao.getAllOnlineDao();
		if (!daoResult.isSuccessed()) {
			return;
		}
		@SuppressWarnings("unchecked")
		List<Online> receivers = daoResult.getResult(List.class);
		Work work = (Work) entity;
		receivers.parallelStream().forEach(
				receiver -> mailCommon.send(new Mail(receiver.getOnlineEmail(), "星之海志愿者公会", work.getWorkPdfpath())));
	}

	@Override
	protected String getHtml() {
		//暂时还不需要推送作品信息
		return null;
	}

}
