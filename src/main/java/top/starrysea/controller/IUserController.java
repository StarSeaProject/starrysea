package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.dto.Admin;

public interface IUserController {
	
	ModelAndView loginController(HttpSession sesson, Admin admin);
}
