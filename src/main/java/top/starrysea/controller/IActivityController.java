package top.starrysea.controller;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.view.in.ActivityForAdd;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.ActivityForModify;
import top.starrysea.object.view.in.ActivityForOne;
import top.starrysea.object.view.in.FundingForAddList;
import top.starrysea.object.view.in.FundingForRemove;

public interface IActivityController {

	Map<String, Object> queryAllActivityControllerAjax(ActivityForAll activity);

	ModelAndView queryActivityController(ActivityForOne activity, BindingResult bindingResult);

	Map<String, Object> queryActivityControllerAjax(ActivityForOne activity, BindingResult bindingResult);

	ModelAndView addActivityController(MultipartFile coverFile, ActivityForAdd activity, BindingResult bindingResult);

	ModelAndView modifyActivityController(ActivityForModify activity, BindingResult bindingResult);

	ModelAndView removeActivityController(ActivityForOne activity, BindingResult bindingResult);

	ModelAndView addFundingController(FundingForAddList fundings, BindingResult bindingResult);

	ModelAndView removeFundingController(FundingForRemove funding, BindingResult bindingResult);
}
