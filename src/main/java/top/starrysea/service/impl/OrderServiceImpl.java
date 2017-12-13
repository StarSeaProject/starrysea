package top.starrysea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IOrderDao;
import top.starrysea.dao.IWorkDao;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.service.IOrderService;

import static top.starrysea.dao.impl.OrderDaoImpl.PAGE_LIMIT;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IWorkDao workDao;

	@Override
	public ServiceResult queryAllOrderService(Condition condition, Orders order) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = orderDao.getAllOrderDao(condition, order);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		List<Orders> ordersList = daoResult.getResult(List.class);
		if (ordersList.isEmpty()) {
			return new ServiceResult("查询结果为空");
		}
		int totalPage = 0;
		daoResult = orderDao.getOrderCountDao(condition, order);
		int count = daoResult.getResult(Integer.class);
		if (count % PAGE_LIMIT == 0) {
			totalPage = count / PAGE_LIMIT;
		} else {
			totalPage = (count / PAGE_LIMIT) + 1;
		}
		result.setSuccessed(true);
		result.setResult(List.class, ordersList);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}

	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	public ServiceResult queryOrderService(Orders order) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = orderDao.getOrderDao(order);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult(daoResult);
		}
		Orders o = daoResult.getResult(Orders.class);
		result.setSuccessed(true);
		result.setResult(Orders.class, o);
		return result;
	}

	@Override
	// 用户对一个作品进行下单，同时减少该作品的库存
	@Transactional
	public ServiceResult addOrderService(Orders order) {
		ServiceResult result = new ServiceResult();
		Work work = order.getWork();
		work.setWorkStock(1);
		DaoResult daoResult = workDao.getStockDao(work);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult("该作品不存在");
		}
		int stock = daoResult.getResult(Integer.class);
		if (stock == 0) {
			return new ServiceResult("该作品已售空");
		} else if (stock - work.getWorkStock() < 0) {
			return new ServiceResult("作品库存不足");
		}
		daoResult=workDao.updateWorkStockDao(work);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult("减少库存失败");
		}
		daoResult=orderDao.saveOrderDao(order);
		if (!daoResult.isSuccessed()) {
			return new ServiceResult("下单失败");
		}
		return result;
	}

	@Override
	// 修改一个订单的状态
	public ServiceResult modifyOrderService(Orders order) {
		return new ServiceResult(orderDao.updateOrderDao(order));
	}

	@Override
	// 删除一个订单
	public ServiceResult removeOrderService(Orders order) {
		return new ServiceResult(orderDao.deleteOrderDao(order));
	}

}
