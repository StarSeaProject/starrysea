package top.starrysea.controller.impl;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.controller.IOrderController;
import top.starrysea.object.dto.Orders;

public class OrderControllerImpl implements IOrderController {

	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	public ModelAndView getOrderController(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 对一个作品进行下单
	public ModelAndView addOrderController(Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 修改一个订单的状态
	public ModelAndView modifyOrderController(HttpSession session, Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	// 删除一个订单
	public ModelAndView removeOrderController(HttpSession session, Orders order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ModelAndView getAllOrderController(Condition condition, Orders order) {
		// TODO Auto-generated method stub
		return null;
	}

}
