package top.starrysea.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.OrderForAll;
import top.starrysea.object.view.in.WorkForAll;

public interface IRootController {

	ModelAndView index();

	void upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file);
	
	ModelAndView queryAllWorkController(Condition condition, WorkForAll work);
	
	ModelAndView queryAllActivityController(Condition condition, ActivityForAll activity);
	
	Map<String, Object> queryAllOrderController(HttpSession session, OrderForAll order);
}
