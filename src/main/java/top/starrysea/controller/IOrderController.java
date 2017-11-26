package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForRemove;

public interface IOrderController {

	ModelAndView queryAllOrderController(HttpSession session, Condition condition, OrderForAll order);

	ModelAndView queryOrderController(OrderForOne order, BindingResult bindingResult);

	ModelAndView addOrderController(OrderForAdd order, BindingResult bindingResult);

	ModelAndView modifyOrderController(HttpSession session, OrderForModify order, BindingResult bindingResult);

	ModelAndView removeOrderController(HttpSession session, OrderForRemove order, BindingResult bindingResult);
}
