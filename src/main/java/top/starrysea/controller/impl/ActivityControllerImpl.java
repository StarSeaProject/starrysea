package top.starrysea.controller.impl;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import top.starrysea.common.Condition;
import top.starrysea.controller.IActivityController;
import top.starrysea.entity.Activity;

public class ActivityControllerImpl implements IActivityController {

	@Override
	// 查询所有众筹活动
	public Model queryAllActivityController(Condition condition, Activity activity) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 查询一个众筹活动的详情页
	public Model queryActivityController(Activity activity) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 添加一个众筹活动
	public Model addActivityController(HttpSession session, Activity activity) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 修改一个众筹活动的状态
	public Model modifyActivityController(HttpSession session, Activity activity) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 删除一个众筹活动
	public Model removeActivityController(HttpSession session, Activity activity) {
		// TODO 自动生成的方法存根
		return null;
	}

}
