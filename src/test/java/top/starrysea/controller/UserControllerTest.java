package top.starrysea.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
import top.starrysea.object.view.in.AdminForLogin;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class UserControllerTest {

	@Autowired
	private IUserController controller;

	@Test
	public void loginController() {
		AdminForLogin admin = new AdminForLogin();
		admin.setAdminPassword("kuma");
		admin.setAdminUseraccount("kuma");
		ModelAndView modelAndView = controller.loginController(admin, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
