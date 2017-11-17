package top.starrysea.controller.impl;

import org.springframework.ui.Model;

import top.starrysea.common.Condition;
import top.starrysea.controller.IOrderController;
import top.starrysea.entity.Orders;

public class OrderControllerImpl implements IOrderController {

	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	public Model getOrderController(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 对一个作品进行下单
	public Model addOrderController(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 修改一个订单的状态
	public Model modifyOrderController(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 删除一个订单
	public Model removeOrderController(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Model getAllOrderController(Condition condition, Orders order) {
		// TODO Auto-generated method stub
		return null;
	}

}
