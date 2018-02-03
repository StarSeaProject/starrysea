package top.starrysea.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.view.in.OrderDetailForAddOrder;
import top.starrysea.object.view.in.OrderDetailForRemoveOrder;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForRemove;

public interface IOrderController {

	Map<String, Object> queryAllOrderController(OrderForAll order);

	ModelAndView queryOrderController(OrderForOne order, BindingResult bindingResult, Device device);

	Map<String, Object> queryOrderControllerAjax(OrderForRemove order, BindingResult bindingResult);

	ModelAndView addOrderController(OrderForAdd order, BindingResult bindingResult, Device device, HttpSession session);

	ModelAndView modifyOrderController(OrderForModify order, BindingResult bindingResult, Device device);

	ModelAndView removeOrderController(OrderForRemove order, BindingResult bindingResult, Device device);

	void exportOrderToXlsController(HttpServletResponse response);

	Map<String, Object> resendEmailController(OrderForRemove order, BindingResult bindingResult);

	Map<String, Object> addWorkToShoppingCarController(HttpSession session, OrderDetailForAddOrder orderDetail,
			BindingResult bindingResult, Device device);

	Map<String, Object> removeWorkFromShoppingCarController(HttpSession session, OrderDetailForRemoveOrder orderDetail,
			BindingResult bindingResult, Device device);

	ModelAndView queryShoppingCarController(HttpSession session, Device device);
}
