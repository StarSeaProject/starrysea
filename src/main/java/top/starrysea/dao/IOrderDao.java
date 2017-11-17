package top.starrysea.dao;

import top.starrysea.common.DaoResult;
import top.starrysea.entity.Orders;

public interface IOrderDao {
	DaoResult getOrderDao(Orders order);

	DaoResult saveOrderDao(Orders order);

	DaoResult updateOrderDao(Orders order);

	DaoResult deleteOrderDao(Orders order);

}
