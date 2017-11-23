package top.starrysea.controller.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IUserController;
import top.starrysea.object.dto.Admin;
import top.starrysea.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {

	@Autowired
	private IUserService userService;

	@Override
	// 管理员登陆
	@RequestMapping("/login")
	public ModelAndView loginController(HttpSession session, Admin admin) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = userService.loginService(admin);
		if (serviceResult.isSuccessed()) {
			Admin admin1 = (Admin) serviceResult.getResult();
			session.setAttribute("adminId", admin1.getAdminId());
			// 登陆成功,返回管理员的主页
			modelAndView.setViewName("admin_index");
		} else {
			// 登陆失败,返回登陆页面
			modelAndView.setViewName("login");
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
		}
		return modelAndView;
	}

}
