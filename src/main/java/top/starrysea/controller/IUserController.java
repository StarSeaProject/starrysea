package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.view.in.AdminForLogin;

public interface IUserController {

	ModelAndView loginController(HttpSession sesson, AdminForLogin admin, BindingResult bindingResult);
}
