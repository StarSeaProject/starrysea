package top.starrysea.controller;

import javax.websocket.Session;

import org.springframework.ui.Model;

import top.starrysea.entity.Admin;

public interface IUserController {
	Model loginController(Session sesson, Admin admin);
}
