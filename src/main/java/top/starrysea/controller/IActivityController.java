package top.starrysea.controller;

import org.springframework.ui.Model;

import top.starrysea.common.Condition;
import top.starrysea.entity.Activity;

public interface IActivityController {
	Model queryAllActivityController(Condition condition, Activity activity);

	Model queryActivityController(Activity activity);

	Model addActivityController(Activity activity);

	Model modifyActivityController(Activity activity);

	Model removeActivityController(Activity activity);
}
