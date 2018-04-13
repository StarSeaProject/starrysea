package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarryseaApplication;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IAdminDao;
import top.starrysea.object.dto.Admin;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class AdminDaoTest {
	@Autowired
	private IAdminDao adminDao;

	@Test
	public void loginDao() {
		DaoResult result = adminDao
				.loginDao(new Admin.Builder().adminUseraccount("kuma").adminPassword("kuma").build());
		System.out.println(result);
	}
}
