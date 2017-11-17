package top.starrysea.service;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Orders;

public interface IOrderService {
	
	ServiceResult getAllOrderService(Condition condition,Orders order);
	
	ServiceResult getOrderService(Orders order);

	ServiceResult addOrderService(Orders order);

	ServiceResult modifyOrderService(Orders order);

	ServiceResult removeOrderService(Orders order);
}
