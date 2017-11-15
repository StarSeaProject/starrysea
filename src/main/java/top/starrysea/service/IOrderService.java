package top.starrysea.service;

import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Order;

public interface IOrderService {
	ServiceResult getOrderService(Order order);

	ServiceResult addOrderService(Order order);

	ServiceResult modifyOrderService(Order order);

	ServiceResult removeOrderService(Order order);
}
