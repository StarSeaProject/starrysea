package top.starrysea.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import top.starrysea.common.ModelAndViewFactory;
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
import static top.starrysea.common.ResultKey.*;

@Controller
public class ActivityControllerImpl implements IActivityController {

	@Autowired
	private IActivityService activityService;

	@Override
	// 查询所有众筹活动
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public ModelAndView queryAllActivityController(ActivityForAll activity, Device device) {
		ServiceResult serviceResult = activityService.queryAllActivityService(activity.getCondition(),
				activity.toDTO());
		List<Activity> result = serviceResult.getResult(LIST_1);
		Activity newestActivity = serviceResult.getResult(ACTIVITY);
		return new ModelAndView(device.isMobile() ? MOBILE + "all_activity" : "all_activity")
				.addObject("newResult", newestActivity.toVoForAll())
				.addObject("result", result.stream().map(Activity::toVoForAll).collect(Collectors.toList()))
				.addObject("nowPage", serviceResult.getNowPage()).addObject("totalPage", serviceResult.getTotalPage());
	}

	@Override
	// 查询所有众筹活动
	@RequestMapping(value = "/activity/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllActivityControllerAjax(@RequestBody ActivityForAll activity) {
		ServiceResult serviceResult = activityService.queryAllActivityService(activity.getCondition(),
				activity.toDTO());
		List<Activity> result = serviceResult.getResult(LIST_1);
		Map<String, Object> theResult = new HashMap<>();
		theResult.put("activityName", activity.getActivityName());
		theResult.put("result", result.stream().map(Activity::toVoForAll).collect(Collectors.toList()));
		theResult.put("nowPage", serviceResult.getNowPage());
		theResult.put("totalPage", serviceResult.getTotalPage());
		return theResult;
	}

	@Override
	// 查询一个众筹活动的详情页
	@RequestMapping(value = "/activity/{activityId}", method = RequestMethod.GET)
	public ModelAndView queryActivityController(@Valid ActivityForOne activity, BindingResult bindingResult,
			Device device) {
		ServiceResult serviceResult = activityService.queryActivityService(activity.toDTO());
		Activity a = serviceResult.getResult(ACTIVITY);
		return new ModelAndView(device.isMobile() ? MOBILE + "activity_detail" : "activity_detail")
				.addObject("activity", a.toVoForOne()).addObject("fundings", serviceResult.getResult(LIST_1))
				.addObject("fundingFactor", serviceResult.getResult(DOUBLE));
	}

	@Override
	// 查询一个众筹活动的详情页
	@RequestMapping(value = "/activity/detail/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryActivityControllerAjax(@RequestBody @Valid ActivityForOne activity,
			BindingResult bindingResult) {
		ServiceResult serviceResult = activityService.queryActivityService(activity.toDTO());
		Activity a = serviceResult.getResult(ACTIVITY);
		Map<String, Object> theResult = new HashMap<>();
		theResult.put("activityId", activity.getActivityId());
		theResult.put("activity", a.toVoForOne());
		theResult.put("fundings", serviceResult.getResult(LIST_1));
		return theResult;
	}

	@Override
	// 添加一个众筹活动
	@RequestMapping(value = "/activity/add", method = RequestMethod.POST)
	public ModelAndView addActivityController(@RequestParam("coverFile") MultipartFile coverFile,
			@Valid ActivityForAdd activity, BindingResult bindingResult, Device device) {
		activityService.addActivityService(coverFile, activity.toDTO(), activity.toDTOImage());
		return ModelAndViewFactory.newSuccessMav("添加成功！", device);
	}

	@Override
	// 修改一个众筹活动的状态
	@RequestMapping(value = "/activity/modify", method = RequestMethod.POST)
	public ModelAndView modifyActivityController(@Valid ActivityForModify activity, BindingResult bindingResult,
			Device device) {
		activityService.modifyActivityService(activity.toDTO());
		return ModelAndViewFactory.newSuccessMav("修改成功!", device);
	}

	@Override
	// 删除一个众筹活动
	@RequestMapping(value = "/activity/remove", method = RequestMethod.POST)
	public ModelAndView removeActivityController(@Valid ActivityForOne activity, BindingResult bindingResult,
			Device device) {
		activityService.removeActivityService(activity.toDTO());
		return ModelAndViewFactory.newSuccessMav("删除成功!", device);
	}

	@Override
	@RequestMapping(value = "/activity/funding/add", method = RequestMethod.POST)
	public ModelAndView addFundingController(@Valid FundingForAddList fundings, BindingResult bindingResult,
			Device device) {
		for (FundingForAdd funding : fundings.getFundings()) {
			funding.setActivityId(fundings.getActivityId());
		}
		activityService.addFundingService(
				fundings.getFundings().stream().map(FundingForAdd::toDTO).collect(Collectors.toList()));
		return ModelAndViewFactory.newSuccessMav("添加成功!", device);
	}

	@Override
	@RequestMapping(value = "/activity/funding/remove", method = RequestMethod.POST)
	public ModelAndView removeFundingController(@Valid FundingForRemove funding, BindingResult bindingResult,
			Device device) {
		activityService.removeFundingService(funding.toDTO());
		return ModelAndViewFactory.newSuccessMav("删除成功!", device);
	}

}
