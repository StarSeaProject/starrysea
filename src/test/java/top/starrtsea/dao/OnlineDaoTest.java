package top.starrtsea.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarryseaApplication;
import top.starrysea.common.Common;
import top.starrysea.dao.IOnlineDao;
import top.starrysea.object.dto.Online;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class OnlineDaoTest {

	@Autowired
	private IOnlineDao dao;
	
	@Test
	public void saveOnlineDao() {
		System.out.println(dao.saveOnlineDao(new Online.Builder().onlineId(Common.getCharId("O-", 10)).onlineEmail("111").build()).getErrInfo());
	}

	@Test
	public void getAllOnlineDao() {
		System.out.println(dao.getAllOnlineDao().getResult(List.class));
	}
}
