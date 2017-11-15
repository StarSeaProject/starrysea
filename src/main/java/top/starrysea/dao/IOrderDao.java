package top.starrysea.dao;

import top.starrysea.common.DaoResult;
import top.starrysea.entity.Order;

public interface IOrderDao {
	DaoResult getOrderDao(Order order);

	DaoResult addOrderDao(Order order);

	DaoResult modifyOrderDao(Order order);

	DaoResult removeOrderDao(Order order);

}
