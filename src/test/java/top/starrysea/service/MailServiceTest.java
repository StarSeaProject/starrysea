package top.starrysea.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.object.dto.Work;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class MailServiceTest {

	@Autowired
	private IMailService service;

	@Test
	public void sendMailService() {
		service.sendMailService(new Work.Builder().workName("作品A").workPdfpath("/asdasd").build());
	}
}
