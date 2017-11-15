package top.starrysea.service.impl;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Work;
import top.starrysea.service.IWorkService;

public class WorkServiceImpl implements IWorkService {

	@Override
	//查询所有作品
	public ServiceResult queryAllWorkService(Condition condition, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//查询一个作品的详情页
	public ServiceResult queryWorkService(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//添加一个作品
	public ServiceResult addWorkService(CommonsMultipartFile file, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//删除一个作品
	public ServiceResult removeWorkService(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

}
