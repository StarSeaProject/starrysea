package top.starrysea.controller;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.OrderForAdd;
import top.starrysea.object.view.in.OrderForModify;
import top.starrysea.object.view.in.OrderForOne;
import top.starrysea.object.view.in.OrderForRemove;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;

public interface IOrderController {

	ModelAndView queryOrderController(OrderForOne order, BindingResult bindingResult);

	Map<String, Object> queryOrderControllerAjax(OrderForRemove order, BindingResult bindingResult);

	ModelAndView addOrderController(OrderForAdd order, BindingResult bindingResult);

	ModelAndView modifyOrderController(OrderForModify order, BindingResult bindingResult);

	ModelAndView removeOrderController(OrderForRemove order, BindingResult bindingResult);
}
