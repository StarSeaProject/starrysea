package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import top.starrysea.common.Condition;
import top.starrysea.entity.Activity;

public interface IActivityController {
	Model queryAllActivityController(Condition condition, Activity activity);

	Model queryActivityController(Activity activity);

	Model addActivityController(HttpSession session, Activity activity);

	Model modifyActivityController(HttpSession session, Activity activity);

	Model removeActivityController(HttpSession session, Activity activity);
}
