package top.starrysea.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import top.starrysea.common.Common;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IOnlineDao;
import top.starrysea.object.dto.Online;
import top.starrysea.object.dto.Work;
import top.starrysea.service.IMailService;

@Service("mailService")
public class MailServiceImpl implements IMailService, InitializingBean {

	@Autowired
	private IOnlineDao onlineDao;
	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	public ServiceResult addMailService(Online online) {
		online.setOnlineId(Common.getCharId("O-", 10));
		return new ServiceResult(onlineDao.saveOnlineDao(online));
	}

	@Override
	public void sendMailService(Work work) {
		DaoResult daoResult = onlineDao.getAllOnlineDao();
		if (!daoResult.isSuccessed()) {
			return;
		}
		List<String> receivers = (List<String>) daoResult.getResult();
		for (String receiver : receivers) {
			threadPool.execute(new MailTask(receiver, work));
		}
		while(true) {}
	}

	private ThreadPoolTaskExecutor threadPool;

	@Override
	public void afterPropertiesSet() throws Exception {
		threadPool = new ThreadPoolTaskExecutor();
		threadPool.setCorePoolSize(Runtime.getRuntime().availableProcessors());
		threadPool.setMaxPoolSize(10);
		threadPool.setQueueCapacity(25);
		threadPool.initialize();
	}

	private class MailTask implements Runnable {

		private String to;
		private Work work;

		public MailTask(String to, Work work) {
			this.to = to;
			this.work = work;
		}

		@Override
		public void run() {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper;
			try {
				mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo(to);
				String nick = MimeUtility.encodeText("星之海志愿者公会");
				mimeMessageHelper.setFrom(new InternetAddress(nick + "<mumuzhizhi@starrysea.top>"));
				mimeMessageHelper.setSubject("星之海志愿者公会推送:" + work.getWorkName());
				mimeMessageHelper.setText(work.getWorkPdfpath());
				mailSender.send(mimeMessage);
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

	}

}
