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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
import top.starrysea.object.view.in.FundingForAdd;
import top.starrysea.object.view.in.FundingForAddList;
import top.starrysea.object.view.in.FundingForRemove;
import top.starrysea.service.IActivityService;

import static top.starrysea.common.Const.*;

@Controller
public class ActivityControllerImpl implements IActivityController {

	@Autowired
	private IActivityService activityService;

	@Override
	// 查询所有众筹活动
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public ModelAndView queryAllActivityController(Condition condition, ActivityForAll activity, Device device) {
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
		Activity newestActivity = serviceResult.getResult(Activity.class);
		modelAndView.addObject("newResult", newestActivity.toVoForAll());
		modelAndView.addObject("result", voResult);
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		// 返回众筹活动的列表页
		modelAndView.setViewName(device.isMobile() ? MOBILE + "all_activity" : "all_activity");
		return modelAndView;
	}

	@Override
	// 查询所有众筹活动
	@RequestMapping(value = "/activity/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllActivityControllerAjax(@RequestBody ActivityForAll activity) {
		Map<String, Object> theResult = new HashMap<>();
		ServiceResult serviceResult = activityService.queryAllActivityService(activity.getCondition(),
				activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			theResult.put(ERRINFO, serviceResult.getErrInfo());
			return theResult;
		}
		List<Activity> result = serviceResult.getResult(List.class);
		List<top.starrysea.object.view.out.ActivityForAll> voResult = result.stream().map(Activity::toVoForAll)
				.collect(Collectors.toList());
		theResult.put("activityName", activity.getActivityName());
		theResult.put("result", voResult);
		theResult.put("nowPage", serviceResult.getNowPage());
		theResult.put("totalPage", serviceResult.getTotalPage());
		return theResult;
	}

	@Override
	// 查询一个众筹活动的详情页
	@RequestMapping(value = "/activity/{activityId}", method = RequestMethod.GET)
	public ModelAndView queryActivityController(@Valid ActivityForOne activity, BindingResult bindingResult,
			Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.queryActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName(device.isNormal() ? ERROR_VIEW : MOBILE + ERROR_VIEW);
			return modelAndView;
		}
		Activity a = serviceResult.getResult(Activity.class);
		modelAndView.addObject("activity", a.toVoForOne());
		modelAndView.addObject("fundings", serviceResult.getResult(List.class));
		// 返回众筹活动的详细页
		modelAndView.setViewName(device.isMobile() ? MOBILE + "activity_detail" : "activity_detail");
		return modelAndView;
	}

	@Override
	// 查询一个众筹活动的详情页
	@RequestMapping(value = "/activity/detail/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryActivityControllerAjax(@RequestBody @Valid ActivityForOne activity,
			BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<String> errInfo = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			theResult.put(ERRINFO, errInfo);
			return theResult;
		}
		ServiceResult serviceResult = activityService.queryActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			theResult.put(ERRINFO, serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			return theResult;
		}
		Activity a = serviceResult.getResult(Activity.class);
		theResult.put("activityId", activity.getActivityId());
		theResult.put("activity", a.toVoForOne());
		theResult.put("fundings", serviceResult.getResult(List.class));
		// 返回众筹活动的详细页
		return theResult;
	}

	@Override
	// 添加一个众筹活动
	@RequestMapping(value = "/activity/add", method = RequestMethod.POST)
	public ModelAndView addActivityController(@RequestParam("coverFile") MultipartFile coverFile,
			@Valid ActivityForAdd activity, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.addActivityService(coverFile, activity.toDTO(),
				activity.toDTOImage());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 添加失败则返回错误页面
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("info", "添加成功！");
		// 添加成功则返回成功页面
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 修改一个众筹活动的状态
	@RequestMapping(value = "/activity/modify", method = RequestMethod.POST)
	public ModelAndView modifyActivityController(@Valid ActivityForModify activity, BindingResult bindingResult,
			Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.modifyActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 修改失败则返回错误页面
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("info", "修改成功!");
		// 修改成功则返回成功页面
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 删除一个众筹活动
	@RequestMapping(value = "/activity/remove", method = RequestMethod.POST)
	public ModelAndView removeActivityController(@Valid ActivityForOne activity, BindingResult bindingResult,
			Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.removeActivityService(activity.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 删除失败则返回错误页面
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		// 删除成功则返回成功页面
		modelAndView.addObject("info", "删除成功!");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/activity/funding/add", method = RequestMethod.POST)
	public ModelAndView addFundingController(@Valid FundingForAddList fundings, BindingResult bindingResult,
			Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		for (FundingForAdd funding : fundings.getFundings()) {
			funding.setActivityId(fundings.getActivityId());
		}
		ServiceResult serviceResult = activityService.addFundingService(
				fundings.getFundings().stream().map(FundingForAdd::toDTO).collect(Collectors.toList()));
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 添加失败则返回错误页面
			modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		// 添加成功则返回成功页面
		modelAndView.addObject("info", "添加成功!");
		modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/activity/funding/remove", method = RequestMethod.POST)
	public ModelAndView removeFundingController(@Valid FundingForRemove funding, BindingResult bindingResult,
			Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = activityService.removeFundingService(funding.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 添加失败则返回错误页面
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		// 添加成功则返回成功页面
		modelAndView.addObject("info", "删除成功!");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

}
