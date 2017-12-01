package top.starrysea.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.object.dto.Admin;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class UserServiceTest {

	@Autowired
	private IUserService service;

	@Test
	public void loginService() {
		System.out.println(
				service.loginService(new Admin.Builder().adminUseraccount("kuma").adminPassword("kuma").build()));
	}

}
