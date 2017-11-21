package top.starrysea.controller.impl;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import top.starrysea.controller.IUserController;
import top.starrysea.entity.Admin;

public class UserControllerImpl implements IUserController {

	@Override
	// 管理员登陆
	public Model loginController(HttpSession session, Admin admin) {
		// TODO 自动生成的方法存根
		return null;
	}

}
