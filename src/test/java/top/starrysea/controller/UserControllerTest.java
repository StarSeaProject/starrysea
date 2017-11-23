package top.starrysea.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
import top.starrysea.object.dto.Admin;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class UserControllerTest {

	@Autowired
	private IUserController controller;
	
	@Test
	public void loginController() {
		ModelAndView modelAndView=controller.loginController(new MockHttpSession(), new Admin.Builder().adminUseraccount("kuma").adminPassword("kuma1").build());
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
