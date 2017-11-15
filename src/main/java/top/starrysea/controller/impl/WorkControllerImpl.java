package top.starrysea.controller.impl;

import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.controller.IWorkController;
import top.starrysea.entity.Work;

public class WorkControllerImpl implements IWorkController {

	@Override
	//查询所有作品，此方法可用于作品管理，也可用于查看旧货
	public Model queryAllWorkController(Condition condition, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//查询一个作品的详情页，此方法可用于作品管理，也可用于查看旧货
	public Model queryWorkController(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//添加一个作品
	public Model addWorkController(CommonsMultipartFile file, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//删除一个作品
	public Model removeWorkController(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
