package top.starrysea.service.impl;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Orders;
import top.starrysea.service.IOrderService;

public class OrderServiceImpl implements IOrderService {

	@Override
	//根据订单号查询一个订单的具体信息以及发货情况
	public ServiceResult getOrderService(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//用户对一个作品进行下单，同时减少该作品的库存
	public ServiceResult addOrderService(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//修改一个订单的状态
	public ServiceResult modifyOrderService(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//删除一个订单
	public ServiceResult removeOrderService(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ServiceResult getAllOrderService(Condition condition, Orders order) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
