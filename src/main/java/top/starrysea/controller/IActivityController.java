package top.starrysea.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.ActivityForAdd;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.ActivityForModify;
import top.starrysea.object.view.in.ActivityForOne;
import top.starrysea.object.view.in.FundingForAddList;
import top.starrysea.object.view.in.FundingForRemove;

public interface IActivityController {
	ModelAndView queryAllActivityController(Condition condition, ActivityForAll activity);

	Map<String, Object> queryAllActivityControllerAjax(ActivityForAll activity);

	ModelAndView queryActivityController(ActivityForOne activity, BindingResult bindingResult);

	Map<String, Object> queryActivityControllerAjax(ActivityForOne activity, BindingResult bindingResult);

	ModelAndView addActivityController(HttpSession session, MultipartFile coverFile, ActivityForAdd activity,
			BindingResult bindingResult);

	ModelAndView modifyActivityController(HttpSession session, ActivityForModify activity, BindingResult bindingResult);

	ModelAndView removeActivityController(HttpSession session, ActivityForOne activity, BindingResult bindingResult);

	ModelAndView addFundingController(HttpSession session, FundingForAddList fundings, BindingResult bindingResult);

	ModelAndView removeFundingController(HttpSession session, FundingForRemove funding, BindingResult bindingResult);
}
