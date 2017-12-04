package top.starrysea.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IOrderController;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForRemove;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.service.IOrderService;
import top.starrysea.service.IWorkService;

@Controller
@RequestMapping(value = "/order")
public class OrderControllerImpl implements IOrderController {

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IWorkService workService;

	@Override
	// 查询所有的作品
	@RequestMapping(value = "/getWorks", method = RequestMethod.GET)
	public ModelAndView queryAllWorkForOrderController(Condition condition, WorkForAll work) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.queryAllWorkService(condition, work.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		List<Work> result = serviceResult.getResult(List.class);
		List<top.starrysea.object.view.out.WorkForAll> voResult = result.stream().map(Work::toVoForAll)
				.collect(Collectors.toList());
		modelAndView.addObject("result", voResult);
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		modelAndView.setViewName("work_orders");
		return modelAndView;
	}

	@Override
	// 查询作品详情
	@RequestMapping(value = "/getWork", method = RequestMethod.GET)
	public ModelAndView queryWorkForOrderController(@Valid WorkForOne work, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.queryWorkService(work.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		Work w = serviceResult.getResult(Work.class);
		modelAndView.addObject("work", w.toVoForOne());
		modelAndView.addObject("workId", work.getWorkId());
		modelAndView.setViewName("work_detail_orders");
		return modelAndView;
	}

	@Override
	// 查询所有的订单
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllOrderController(HttpSession session, Condition condition,
			@RequestBody OrderForAll order) {
		Map<String, Object> theResult = new HashMap<>();
		if (session.getAttribute("adminId") == null) {
			theResult.put("errInfo", "重新登陆!");
			return theResult;
		}
		ServiceResult serviceResult = orderService.queryAllOrderService(condition, order.toDTO());
		if (!serviceResult.isSuccessed()) {
			theResult.put("errInfo", serviceResult.getErrInfo());
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
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView queryOrderController(@Valid OrderForOne order, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		Orders o = serviceResult.getResult(Orders.class);
		modelAndView.addObject("orders", o.toVoForOne());
		modelAndView.setViewName("orders_details");
		return modelAndView;
	}

	// 根据订单号查询一个订单的具体信息以及发货情况
	@RequestMapping(value = "/detail/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryOrderControllerAjax(@RequestBody @Valid OrderForRemove order,
			BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<String> errInfo = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			theResult.put("errInfo", errInfo);
			return theResult;
		}
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			theResult.put("errInfo", serviceResult.getErrInfo());
			return theResult;
		}
		Orders o = serviceResult.getResult(Orders.class);
		theResult.put("orders", o.toVoForOne());
		theResult.put("orderId", order.getOrderId());
		return theResult;
	}

	@RequestMapping(value = "/toAddOrder", method = RequestMethod.GET)
	public ModelAndView gotoAddOrder(@Valid WorkForOne work) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("workId", work.getWorkId());
		modelAndView.setViewName("add_order");
		return modelAndView;
	}

	@Override
	// 对一个作品进行下单
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addOrderController(@Valid OrderForAdd order, BindingResult bindingResult) {
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
		modelAndView.addObject("info", "下单成功!");
		modelAndView.setViewName("success");
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
			return new ModelAndView("admin_login");
		}
		ServiceResult serviceResult = orderService.modifyOrderService(order.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			modelAndView.setViewName("error");
			return modelAndView;
		}
		modelAndView.addObject("info", "修改成功！");
		modelAndView.setViewName("success");
		return modelAndView;
	}

	@Override
	// 删除一个订单
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ModelAndView removeOrderController(HttpSession session, @Valid OrderForRemove order,
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
		modelAndView.addObject("info", "删除成功!");
		modelAndView.setViewName("success");
		return modelAndView;
	}

}
