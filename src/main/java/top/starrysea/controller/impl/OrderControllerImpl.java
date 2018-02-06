package top.starrysea.controller.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.WorkType;
import top.starrysea.object.view.in.OrderDetailForAddOrder;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForRemove;
import top.starrysea.object.view.in.WorkTypeForToAddOrders;
import top.starrysea.object.view.out.WorkTypeForRemoveCar;
import top.starrysea.service.IOrderService;

import static top.starrysea.common.Const.*;
import static top.starrysea.common.ResultKey.*;

@Controller
public class OrderControllerImpl implements IOrderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IOrderService orderService;

	@Override
	// 查询所有的订单
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllOrderController(@RequestBody OrderForAll order) {
		Map<String, Object> theResult = new HashMap<>();
		ServiceResult serviceResult = orderService.queryAllOrderService(order.getCondition(), order.toDTO());
		List<Orders> result = serviceResult.getResult(ORDER_LIST);
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
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		Orders o = serviceResult.getResult(ORDER_DETAIL);
		List<OrderDetail> ods = serviceResult.getResult(ORDER_DETAIL_LIST);
		modelAndView.addObject("order", o.toVoForOne());
		modelAndView.addObject("orderDetails", ods.stream().map(OrderDetail::toVoForOne).collect(Collectors.toList()));
		modelAndView.setViewName(device.isMobile() ? MOBILE + "orders_details" : "orders_details");
		return modelAndView;
	}

	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	@RequestMapping(value = "/order/detail/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryOrderControllerAjax(@RequestBody @Valid OrderForRemove order,
			BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		Orders o = serviceResult.getResult(ORDER_DETAIL);
		theResult.put("orders", o.toVoForOne());
		theResult.put("orderId", order.getOrderId());
		return theResult;
	}

	@RequestMapping(value = "/order/toAddOrder", method = RequestMethod.POST)
	public ModelAndView gotoAddOrder(@Valid WorkTypeForToAddOrders workTypes, Device device, HttpSession session) {
		ServiceResult sr = orderService.queryWorkTypeStock(workTypes.toDTO());
		ModelAndView modelAndView = new ModelAndView();
		if (!sr.isSuccessed()) {
			modelAndView.addObject(ERRINFO, sr.getErrInfo());
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("workTypes", workTypes);
		modelAndView.addObject("provinces", orderService.queryAllProvinceService().getResult(ORDER_ADDRESS));
		String token = Common.getCharId(10);
		session.setAttribute(TOKEN, token);
		modelAndView.addObject(TOKEN, token);
		modelAndView.setViewName(device.isMobile() ? MOBILE + "add_order" : "add_order");
		return modelAndView;
	}

	@Override
	// 对一个作品进行下单
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	public ModelAndView addOrderController(@Valid OrderForAdd order, BindingResult bindingResult, Device device,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (!order.getToken().equals(session.getAttribute(TOKEN))) {
			modelAndView.addObject(ERRINFO, "您已经下单,请勿再次提交");
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		session.removeAttribute(TOKEN);
		ServiceResult serviceResult = orderService.addOrderService(order.toDTO(), order.toDTOOrderDetail());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject(INFO, "您已下单成功，之后将会为您派送！");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 修改一个订单的状态
	@RequestMapping(value = "/order/modify/{orderId}", method = RequestMethod.POST)
	public ModelAndView modifyOrderController(@Valid OrderForModify order, BindingResult bindingResult, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		orderService.modifyOrderService(order.toDTO());
		modelAndView.addObject(INFO, "发货成功！");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 删除一个订单
	@RequestMapping(value = "/order/remove/{orderId}", method = RequestMethod.POST)
	public ModelAndView removeOrderController(@Valid OrderForRemove order, BindingResult bindingResult, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		orderService.removeOrderService(order.toDTO());
		modelAndView.addObject(INFO, "删除成功!");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/order/export", method = RequestMethod.GET)
	public void exportOrderToXlsController(HttpServletResponse response) {
		orderService.exportOrderToXlsService();
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + "result.xls");
		try {
			byte[] buff = Files.readAllBytes(Paths.get("/result.xls"));
			response.getOutputStream().write(buff);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	@RequestMapping(value = "/order/resend", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resendEmailController(@RequestBody @Valid OrderForRemove order,
			BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		if (bindingResult.hasErrors()) {
			return Common.handleVaildErrorForAjax(bindingResult);
		}
		ServiceResult sr = orderService.resendEmailService(order.toDTO());
		if (!sr.isSuccessed()) {
			theResult.put(ERRINFO, sr.getErrInfo());
			return theResult;
		}
		theResult.put("result", "success");
		return theResult;
	}

	@Override
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addWorkToShoppingCarController(HttpSession session,
			@RequestBody @Valid OrderDetailForAddOrder orderDetail, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildErrorForAjax(bindingResult);
		}
		List<OrderDetailForAddOrder> orderDetailList = (List<OrderDetailForAddOrder>) session.getAttribute(SHOPPINGCAR);
		if (orderDetailList == null) {
			orderDetailList = new ArrayList<>();
		}
		Map<String, Object> theResult = new HashMap<>();
		for (OrderDetailForAddOrder orderDetailForAddOrder : orderDetailList) {
			if (orderDetailForAddOrder.getWorkId() == orderDetail.getWorkId()) {
				theResult.put(INFO, "您已经将该作品放入购物车,不能重复放入");
				return theResult;
			}
		}
		orderDetailList.add(orderDetail);
		session.setAttribute(SHOPPINGCAR, orderDetailList);
		theResult.put(INFO, "添加到购物车成功!");
		return theResult;
	}

	@Override
	@RequestMapping(value = "/car/remove/{index}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView removeWorkFromShoppingCarController(HttpSession session, @Valid WorkTypeForRemoveCar workType,
			BindingResult bindingResult, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute(TOKEN) == null || !session.getAttribute(TOKEN).equals(workType.getToken())) {
			modelAndView.addObject(ERRINFO, "您已经删除该作品,请勿再次提交");
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		session.removeAttribute(TOKEN);
		List<OrderDetailForAddOrder> orderDetailList = (List<OrderDetailForAddOrder>) session.getAttribute(SHOPPINGCAR);
		orderDetailList.remove((int) workType.getIndex());
		session.setAttribute(SHOPPINGCAR, orderDetailList);
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		modelAndView.addObject(INFO, "从购物车移除作品成功!");
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	public ModelAndView queryShoppingCarController(HttpSession session, Device device) {
		List<OrderDetailForAddOrder> orderDetailList = (List<OrderDetailForAddOrder>) session.getAttribute(SHOPPINGCAR);
		if (orderDetailList == null) {
			orderDetailList = new ArrayList<>();
		}
		ModelAndView modelAndView = new ModelAndView(device.isMobile() ? MOBILE + "shopcar" : "shopcar");
		List<WorkType> workTypes = orderService.queryAllWorkTypeForShoppingCarService(orderDetailList.stream()
				.map(orderDetail -> new WorkType.Builder().workTypeId(orderDetail.getWorkTypeId()).build())
				.collect(Collectors.toList())).getResult(WORK_DETAIL_TYPE);
		modelAndView.addObject("workTypes", workTypes.stream().map(WorkType::toVoForCar).collect(Collectors.toList()));
		String token = Common.getCharId(10);
		session.setAttribute(TOKEN, token);
		modelAndView.addObject(TOKEN, token);
		return modelAndView;
	}
}
