package top.starrysea.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForRemove;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class OrderControllerTest {

	@Autowired
	private IOrderController orderController;

	@Test
	public void queryOrderController() {
		OrderForOne order = new OrderForOne();
		System.out.println(orderController.queryOrderController(order, null));
	}

	@Test
	public void addOrderController() {
		OrderForAdd order = new OrderForAdd();
		ModelAndView modelAndView = orderController.addOrderController(order, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void modifyOrderController() {
		OrderForModify order = new OrderForModify();
		ModelAndView modelAndView = orderController.modifyOrderController(order, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void removeOrderController() {
		OrderForRemove order = new OrderForRemove();
		order.setOrderId("1");
		ModelAndView modelAndView = orderController.removeOrderController(order, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
