package top.starrysea.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IUserController;
import top.starrysea.object.dto.Admin;
import top.starrysea.object.view.in.AdminForLogin;
import top.starrysea.service.IUserService;

import static top.starrysea.common.Const.*;
import static top.starrysea.common.ResultKey.ADMIN;

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {

	@Autowired
	private IUserService userService;

	@Override
	// 管理员登陆
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginController(@Valid AdminForLogin admin, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = userService.loginService(admin.toDTO());
		if (serviceResult.isSuccessed()) {
			Admin admin1 = serviceResult.getResult(ADMIN);
			// 登陆成功,返回管理员的主页
			modelAndView.addObject(ADMIN_SESSION_KEY, admin1.getAdminId());
			modelAndView.setViewName(device.isMobile() ? MOBILE + BOSS : BOSS);
		} else {
			// 登陆失败,返回登陆页面
			modelAndView.setViewName(device.isMobile() ? MOBILE + LOGIN_VIEW : LOGIN_VIEW);
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
		}
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public ModelAndView exitController(Device device) {
		return new ModelAndView(device.isMobile() ? MOBILE + LOGIN_VIEW : LOGIN_VIEW);
	}

}
