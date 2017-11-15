package top.starrysea.dao.impl;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IWorkDao;
import top.starrysea.entity.Work;

public class WorkDaoImpl implements IWorkDao {

	@Override
	//查询所有作品
	public DaoResult getAllWorkDao(Condition condition, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//查询所有作品的数量，用于分页
	public DaoResult getWorkCountDao(Condition condition, Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//查询一个作品的详情页
	public DaoResult getWorkDao(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//添加一个作品
	public DaoResult saveWorkDao(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//管理员删除一个作品
	public DaoResult deleteWorkDao(Work work) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//减少一个作品的库存
	public DaoResult updateWorkStockDao(int stock) {
		// TODO 自动生成的方法存根
		return null;
	}

}
