package top.starrysea.controller;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.view.in.AdminForLogin;

public interface IUserController {

	ModelAndView loginController(AdminForLogin admin, BindingResult bindingResult, Device device);
	
	ModelAndView exitController(Device device);
}
