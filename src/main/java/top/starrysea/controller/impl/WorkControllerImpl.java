package top.starrysea.controller.impl;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.controller.IWorkController;
import top.starrysea.object.dto.Work;

public class WorkControllerImpl implements IWorkController {

	@Override
	// 查询所有作品，此方法可用于作品管理，也可用于查看旧货
	public ModelAndView queryAllWorkController(Condition condition, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 查询一个作品的详情页，此方法可用于作品管理，也可用于查看旧货
	public ModelAndView queryWorkController(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 添加一个作品
	public ModelAndView addWorkController(HttpSession session, MultipartFile file, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 删除一个作品
	public ModelAndView removeWorkController(HttpSession session, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

}
