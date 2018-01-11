package top.starrysea.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IOrderDao;
import top.starrysea.dao.IWorkDao;
import top.starrysea.exception.EmptyResultException;
import top.starrysea.exception.LogicException;
import top.starrysea.exception.UpdateException;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.service.IOrderService;

import static top.starrysea.dao.impl.OrderDaoImpl.PAGE_LIMIT;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IWorkDao workDao;

	@Override
	public ServiceResult queryAllOrderService(Condition condition, Orders order) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = orderDao.getAllOrderDao(condition, order);
		List<Orders> ordersList = daoResult.getResult(List.class);
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
		Orders o = daoResult.getResult(Orders.class);
		result.setSuccessed(true);
		result.setResult(Orders.class, o);
		return result;
	}

	@Override
	// 用户对一个作品进行下单，同时减少该作品的库存
	@Transactional
	public ServiceResult addOrderService(Orders order) {
		try {
			Work work = order.getWork();
			work.setWorkStock(1);
			DaoResult daoResult = workDao.getStockDao(work);
			int stock = daoResult.getResult(Integer.class);
			if (stock == 0) {
				throw new EmptyResultException("该作品已售空");
			} else if (stock - work.getWorkStock() < 0) {
				throw new LogicException("作品库存不足");
			}
			workDao.updateWorkStockDao(work);
			order.setOrderId(Common.getCharId("O-", 10));
			orderDao.saveOrderDao(order);
			daoResult = workDao.getWorkDao(order.getWork());
			order.setWork(daoResult.getResult(Work.class));
			ServiceResult serviceResult = new ServiceResult(true);
			serviceResult.setResult(Orders.class, order);
			return serviceResult;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}

	}

	@Override
	// 修改一个订单的状态
	public ServiceResult modifyOrderService(Orders order) {
		orderDao.updateOrderDao(order);
		return new ServiceResult(orderDao.getOrderDao(order));
	}

	@Override
	// 删除一个订单
	public ServiceResult removeOrderService(Orders order) {
		return new ServiceResult(orderDao.deleteOrderDao(order));
	}

}
