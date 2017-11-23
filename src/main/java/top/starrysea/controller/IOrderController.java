package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.dto.Orders;

public interface IOrderController {

	ModelAndView getAllOrderController(Condition condition, Orders order);

	ModelAndView getOrderController(Orders order);

	ModelAndView addOrderController(Orders order);

	ModelAndView modifyOrderController(HttpSession session, Orders order);

	ModelAndView removeOrderController(HttpSession session, Orders order);
}
