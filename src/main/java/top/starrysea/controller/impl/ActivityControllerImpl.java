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
import top.starrysea.controller.IActivityController;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.view.in.ActivityForAdd;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.ActivityForModify;
import top.starrysea.object.view.in.ActivityForOne;
import top.starrysea.service.IActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityControllerImpl implements IActivityController {

	@Autowired
	private IActivityService activityService;

	@Override
	// 查询所有众筹活动
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView queryAllActivityController(Condition condition, ActivityForAll activity) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.queryAllActivityService(condition, activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName("error");
			return modelAndView;
		}
		List<Activity> result = serviceResult.getResult(List.class);
		List<top.starrysea.object.view.out.ActivityForAll> voResult = result.stream().map(Activity::toVoForAll).collect(Collectors.toList());
		modelAndView.addObject("result", voResult);
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		// 返回众筹活动的列表页
		modelAndView.setViewName("all_activity");
		return modelAndView;
	}

	@Override
	// 查询一个众筹活动的详情页
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView queryActivityController(@Valid ActivityForOne activity, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.queryActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName("error");
			return modelAndView;
		}
		Activity a = serviceResult.getResult(Activity.class);
		modelAndView.addObject("activity", a.toVoForOne());
		// 返回众筹活动的详细页
		modelAndView.setViewName("activity_detail");
		return modelAndView;
	}

	@Override
	// 添加一个众筹活动
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addActivityController(HttpSession session, @Valid ActivityForAdd activity,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("adminId") == null) {
			// 如果当前没有管理员账号登陆,则拦截并返回登陆页面
			return new ModelAndView("login");
		}
		ServiceResult serviceResult = activityService.addActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName("error");
			return modelAndView;
		}
		// 添加成功则返回成功页面
		modelAndView.setViewName("add_success");
		return modelAndView;
	}

	@Override
	// 修改一个众筹活动的状态
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modifyActivityController(HttpSession session, @Valid ActivityForModify activity,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("adminId") == null) {
			// 如果当前没有管理员账号登陆,则拦截并返回登陆页面
			return new ModelAndView("login");
		}
		ServiceResult serviceResult = activityService.modifyActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName("error");
			return modelAndView;
		}
		// 修改成功则返回成功页面
		modelAndView.setViewName("modify_success");
		return modelAndView;
	}

	@Override
	// 删除一个众筹活动
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ModelAndView removeActivityController(HttpSession session, @Valid ActivityForOne activity,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("adminId") == null) {
			// 如果当前没有管理员账号登陆,则拦截并返回登陆页面
			return new ModelAndView("login");
		}
		ServiceResult serviceResult = activityService.removeActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName("error");
			return modelAndView;
		}
		// 修改成功则返回成功页面
		modelAndView.setViewName("remove_success");
		return modelAndView;
	}

}
