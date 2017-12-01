package top.starrysea.controller.impl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {

	@Autowired
	private IUserService userService;

	@Override
	// 管理员登陆
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginController(HttpSession session, @Valid AdminForLogin admin, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = userService.loginService(admin.toDTO());
		if (serviceResult.isSuccessed()) {
			Admin admin1 = serviceResult.getResult(Admin.class);
			session.setAttribute("adminId", admin1.getAdminId());
			// 登陆成功,返回管理员的主页
			modelAndView.setViewName("boss");
		} else {
			// 登陆失败,返回登陆页面
			modelAndView.setViewName("login");
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
		}
		return modelAndView;
	}

}
