package top.starrysea.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IOrderController;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForRemove;
import top.starrysea.object.view.in.WorkForOne;
import top.starrysea.service.IOrderService;

import static top.starrysea.common.Const.*;

@Controller
public class OrderControllerImpl implements IOrderController {

	@Autowired
	private IOrderService orderService;

	@Override
	// 查询所有的订单
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllOrderController(@RequestBody OrderForAll order) {
		Map<String, Object> theResult = new HashMap<>();
		ServiceResult serviceResult = orderService.queryAllOrderService(order.getCondition(), order.toDTO());
		if (!serviceResult.isSuccessed()) {
			theResult.put(ERRINFO, serviceResult.getErrInfo());
			return theResult;
		}
		List<Orders> result = serviceResult.getResult(List.class);
		List<top.starrysea.object.view.out.OrderForAll> voResult = result.stream().map(Orders::toVoForAll)
				.collect(Collectors.toList());
		theResult.put("orderName", order.getOrderName());
		theResult.put("result", voResult);
		theResult.put("nowPage", serviceResult.getNowPage());
		theResult.put("totalPage", serviceResult.getTotalPage());
		return theResult;
	}
	
	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	@RequestMapping(value = "/order/{orderNum}", method = RequestMethod.GET)
	public ModelAndView queryOrderController(@Valid OrderForOne order, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(device.isNormal() ? ERROR_VIEW : MOBILE + ERROR_VIEW);
			return modelAndView;
		}
		Orders o = serviceResult.getResult(Orders.class);
		modelAndView.addObject("orders", o.toVoForOne());
		modelAndView.setViewName(device.isNormal() ? "orders_details" : MOBILE + "orders_details");
		return modelAndView;
	}

	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	@RequestMapping(value = "/order/detail/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryOrderControllerAjax(@RequestBody @Valid OrderForRemove order,
			BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<String> errInfo = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			theResult.put(ERRINFO, errInfo);
			return theResult;
		}
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			theResult.put(ERRINFO, serviceResult.getErrInfo());
			return theResult;
		}
		Orders o = serviceResult.getResult(Orders.class);
		theResult.put("orders", o.toVoForOne());
		theResult.put("orderId", order.getOrderId());
		return theResult;
	}

	@RequestMapping(value = "/order/toAddOrder", method = RequestMethod.GET)
	public ModelAndView gotoAddOrder(@Valid WorkForOne work, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("workId", work.getWorkId());
		modelAndView.setViewName(device.isNormal() ? "add_order" : MOBILE + "add_order");
		return modelAndView;
	}

	@Override
	// 对一个作品进行下单
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	public ModelAndView addOrderController(@Valid OrderForAdd order, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.addOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(device.isNormal() ? ERROR_VIEW : MOBILE + ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("info", "下单成功!");
		modelAndView.setViewName(device.isNormal() ? SUCCESS_VIEW : MOBILE + SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 修改一个订单的状态
	@RequestMapping(value = "/order/modify", method = RequestMethod.POST)
	public ModelAndView modifyOrderController(@Valid OrderForModify order, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.modifyOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(device.isNormal() ? ERROR_VIEW : MOBILE + ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("info", "修改成功！");
		modelAndView.setViewName(device.isNormal() ? SUCCESS_VIEW : MOBILE + SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 删除一个订单
	@RequestMapping(value = "/order/remove", method = RequestMethod.POST)
	public ModelAndView removeOrderController(@Valid OrderForRemove order, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.removeOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(device.isNormal() ? ERROR_VIEW : MOBILE + ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("info", "删除成功!");
		modelAndView.setViewName(device.isNormal() ? SUCCESS_VIEW : MOBILE + SUCCESS_VIEW);
		return modelAndView;
	}

}
