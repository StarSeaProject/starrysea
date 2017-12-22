package top.starrysea.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
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
		ModelAndView modelAndView = controller.queryAllActivityController(condition, activity,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void queryAllOrderController() {
		OrderForAll order = new OrderForAll();
		order.setPage(1);
		System.out.println(controller.queryAllOrderController(order));
	}

	@Test
	public void queryAllWorkController() {
		Condition condition = new Condition();
		condition.setPage(1);
		WorkForAll work = new WorkForAll();
		Device device = null;
		ModelAndView modelAndView = controller.queryAllWorkController(condition, work, device);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
