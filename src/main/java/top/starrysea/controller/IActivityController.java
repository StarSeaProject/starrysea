package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.ActivityForAdd;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.ActivityForModify;
import top.starrysea.object.view.in.ActivityForOne;

public interface IActivityController {
	ModelAndView queryAllActivityController(Condition condition, ActivityForAll activity, BindingResult bindingResult);

	ModelAndView queryActivityController(ActivityForOne activity, BindingResult bindingResult);

	ModelAndView addActivityController(HttpSession session, ActivityForAdd activity, BindingResult bindingResult);

	ModelAndView modifyActivityController(HttpSession session, ActivityForModify activity, BindingResult bindingResult);

	ModelAndView removeActivityController(HttpSession session, ActivityForOne activity, BindingResult bindingResult);
}
