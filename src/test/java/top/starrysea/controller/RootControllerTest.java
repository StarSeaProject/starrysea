package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.object.view.in.WorkForAll;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class RootControllerTest {

	@Autowired
	private IRootController controller;
	
	@Test
	public void queryAllActivityController() {
		Condition condition = new Condition();
		condition.setPage(1);
		ActivityForAll activity = new ActivityForAll();
		ModelAndView modelAndView = controller.queryAllActivityController(condition, activity);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
	
	@Test
	public void queryAllOrderController() {
		HttpSession session = new MockHttpSession();
		session.setAttribute("adminId", 1);
		OrderForAll order = new OrderForAll();
		order.setPage(1);
		System.out.println(controller.queryAllOrderController(session, order));
	}
	
	@Test
	public void queryAllWorkController() {
		Condition condition = new Condition();
		condition.setPage(1);
		HttpSession session = new MockHttpSession();
		session.setAttribute("adminId", 1);
		WorkForAll work = new WorkForAll();
		ModelAndView modelAndView = controller.queryAllWorkController(condition, work);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
