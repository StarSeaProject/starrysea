package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import top.starrysea.entity.Admin;

public interface IUserController {
	
	Model loginController(HttpSession sesson, Admin admin);
	
}
