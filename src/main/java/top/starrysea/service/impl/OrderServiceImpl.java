package top.starrysea.service.impl;

import org.springframework.ui.Model;

import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Order;
import top.starrysea.service.IOrderService;

public class OrderServiceImpl implements IOrderService {

	@Override
	//根据订单号查询一个订单的具体信息以及发货情况
	public Model getOrderService(Order order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//用户对一个作品进行下单，同时减少该作品的库存
	public ServiceResult addOrderService(Order order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//修改一个订单的状态
	public ServiceResult modifyOrderService(Order order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//删除一个订单
	public ServiceResult removeOrderService(Order order) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
