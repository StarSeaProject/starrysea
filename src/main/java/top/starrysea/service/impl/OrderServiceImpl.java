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
import top.starrysea.dao.IWorkTypeDao;
import top.starrysea.exception.EmptyResultException;
import top.starrysea.exception.LogicException;
import top.starrysea.exception.UpdateException;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;
import top.starrysea.service.IOrderService;

import static top.starrysea.dao.impl.OrderDaoImpl.PAGE_LIMIT;
import static top.starrysea.common.ResultKey.*;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IWorkDao workDao;
	@Autowired
	private IWorkTypeDao workTypeDao;

	@Override
	public ServiceResult queryAllOrderService(Condition condition, Orders order) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = orderDao.getAllOrderDao(condition, order);
		@SuppressWarnings("unchecked")
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
		result.setResult(ORDER_LIST, ordersList);
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
		result.setResult(ORDER_DETAIL, o);
		return result;
	}

	@Override
	// 用户对一个作品进行下单，同时减少该作品的库存
	@Transactional
	public ServiceResult addOrderService(Orders order) {
		try {
			WorkType workType = order.getWorkType();
			workType.setStock(1);
			DaoResult daoResult = workTypeDao.getWorkTypeStockDao(workType);
			int stock = daoResult.getResult(Integer.class);
			if (stock == 0) {
				throw new EmptyResultException("该作品已售空");
			} else if (stock - workType.getStock() < 0) {
				throw new LogicException("作品库存不足");
			}
			workTypeDao.reduceWorkTypeStockDao(workType);
			order.setOrderId(Common.getCharId("O-", 10));
			orderDao.saveOrderDao(order);
			WorkType wt = workTypeDao.getWorkTypeNameDao(workType).getResult(WorkType.class);
			wt.setWork(workDao.getWorkDao(order.getWorkType().getWork()).getResult(Work.class));
			order.setWorkType(wt);
			ServiceResult serviceResult = new ServiceResult(true);
			serviceResult.setResult(ORDER_DETAIL, order);
			return serviceResult;
		} catch (EmptyResultException | LogicException e) {
			ServiceResult serviceResult = new ServiceResult(false);
			serviceResult.setErrInfo(e.getMessage());
			return serviceResult;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}

	}

	@Override
	// 修改一个订单的状态
	public ServiceResult modifyOrderService(Orders order) {
		order.setOrderStatus((short) 2);
		orderDao.updateOrderDao(order);
		ServiceResult sr = new ServiceResult(true);
		sr.setResult(ORDER_DETAIL, orderDao.getOrderDao(order).getResult(Orders.class));
		return sr;
	}

	@Override
	// 删除一个订单
	public ServiceResult removeOrderService(Orders order) {
		return new ServiceResult(true);
	}

}
