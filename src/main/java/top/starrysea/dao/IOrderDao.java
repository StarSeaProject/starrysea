package top.starrysea.dao;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Orders;

public interface IOrderDao {

	DaoResult getAllOrderDao(Condition condition, Orders order);

	DaoResult getOrderCountDao(Condition condition, Orders order);

	DaoResult getOrderDao(Orders order);

	DaoResult saveOrderDao(Orders order);

	DaoResult updateOrderDao(Orders order);

	DaoResult deleteOrderDao(Orders order);

	DaoResult getAllOrderForXls();
}
