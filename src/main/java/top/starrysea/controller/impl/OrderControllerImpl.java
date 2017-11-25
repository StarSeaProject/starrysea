package top.starrysea.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IOrderController;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.service.IOrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderControllerImpl implements IOrderController {
	@Autowired
	private IOrderService orderService;

	@Override
	// 查询所有的订单
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView queryAllOrderController(HttpSession session, Condition condition, OrderForAll order) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("adminId") == null) {
			return new ModelAndView("login");
		}
		ServiceResult serviceResult = orderService.queryAllOrderService(condition, order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		List<Orders> result = (List<Orders>) serviceResult.getResult();
		List<top.starrysea.object.view.out.OrderForAll> voResult = result.stream().map(Orders::toVoForAll)
				.collect(Collectors.toList());
		modelAndView.addObject("result", voResult);
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		modelAndView.setViewName("all_orders");
		return modelAndView;
	}

	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView queryOrderController(@Valid OrderForOne order, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.queryOrderService(order.toDto());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		Orders o = (Orders) serviceResult.getResult();
		modelAndView.addObject("orders", o.toVoForOne());
		modelAndView.setViewName("orders_details");
		return modelAndView;
	}

	@Override
	// 对一个作品进行下单
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addOrderController(OrderForAdd order, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.addOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		modelAndView.setViewName("add_success");
		return modelAndView;
	}

	@Override
	// 修改一个订单的状态
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modifyOrderController(HttpSession session, @Valid OrderForModify order,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("adminId") == null) {
			return new ModelAndView("login");
		}
		ServiceResult serviceResult = orderService.modifyOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		modelAndView.setViewName("modify_success");
		return modelAndView;
	}

	@Override
	// 删除一个订单
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ModelAndView removeOrderController(HttpSession session, @Valid OrderForModify order,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		if (session.getAttribute("adminId") == null) {
			return new ModelAndView("login");
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.removeOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		modelAndView.setViewName("remove_success");
		return modelAndView;
	}

}
