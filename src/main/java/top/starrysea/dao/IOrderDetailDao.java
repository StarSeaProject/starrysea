package top.starrysea.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.OrderDetail;

public interface IOrderDetailDao {

	DaoResult getAllOrderDetailDao(OrderDetail orderDetail);
	
	DaoResult saveOrderDetailsDao(List<OrderDetail> orderDetails);
	
	DaoResult isOrderDetailExistDao(OrderDetail orderDetail);
	
	DaoResult getAllOrderDetailForXls();
}
