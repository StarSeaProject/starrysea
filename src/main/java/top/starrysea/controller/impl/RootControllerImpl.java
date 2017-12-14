package top.starrysea.controller.impl;

import static top.starrysea.common.Const.ADMIN_SESSION_KEY;
import static top.starrysea.common.Const.ERRINFO;
import static top.starrysea.common.Const.ERROR_VIEW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IRootController;
import top.starrysea.file.FileCondition;
import top.starrysea.file.FileType;
import top.starrysea.file.FileUtil;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.service.IActivityService;
import top.starrysea.service.IOrderService;
import top.starrysea.service.IWorkService;

@Controller
public class RootControllerImpl implements IRootController {

	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private IWorkService workService;
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IOrderService orderService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping("/intro")
	public ModelAndView intro() {
		return new ModelAndView("intro");
	}

	@RequestMapping("/admin")
	public ModelAndView admin() {
		return new ModelAndView("admin_login");
	}

	@Override
	@RequestMapping("/uploads")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		String filePath = null;
		try {
			filePath=fileUtil.saveFile(file, FileCondition.of(FileType.IMG, ""));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		Map<String, Object> result = new HashMap<>();
		List<String> data = new ArrayList<>();
		data.add(filePath);
		result.put("errno", 0);
		result.put("data", data);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String theResult = mapper.writeValueAsString(result);
			response.getWriter().write(theResult);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	@RequestMapping(value = "/work", method = RequestMethod.GET)
	// 查询所有作品，此方法可用于作品管理，也可用于查看旧货
	public ModelAndView queryAllWorkController(Condition condition, WorkForAll work) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.queryAllWorkService(condition, work.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(ERROR_VIEW);
			return modelAndView;
		}
		List<Work> result = serviceResult.getResult(List.class);
		List<top.starrysea.object.view.out.WorkForAll> voResult = result.stream().map(Work::toVoForAll)
				.collect(Collectors.toList());
		modelAndView.addObject("result", voResult);
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		modelAndView.setViewName("work");
		return modelAndView;
	}
	
	@Override
	// 查询所有众筹活动
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public ModelAndView queryAllActivityController(Condition condition, ActivityForAll activity) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.queryAllActivityService(condition, activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName(ERROR_VIEW);
			return modelAndView;
		}
		List<Activity> result = serviceResult.getResult(List.class);
		List<top.starrysea.object.view.out.ActivityForAll> voResult = result.stream().map(Activity::toVoForAll)
				.collect(Collectors.toList());
		modelAndView.addObject("newResult", voResult.get(0));
		modelAndView.addObject("result", voResult.subList(1, voResult.size()));
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		// 返回众筹活动的列表页
		modelAndView.setViewName("all_activity");
		return modelAndView;
	}
	
	@Override
	// 查询所有的订单
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllOrderController(HttpSession session, @RequestBody OrderForAll order) {
		Map<String, Object> theResult = new HashMap<>();
		if (session.getAttribute(ADMIN_SESSION_KEY) == null) {
			theResult.put(ERRINFO, "重新登陆!");
			return theResult;
		}
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
}
