package top.starrysea.service;

import org.springframework.ui.Model;

import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Order;

public interface IOrderService {
	Model getOrderService(Order order);
	
	ServiceResult addOrderService(Order order);
	
	ServiceResult modifyOrderService(Order order);
	
	ServiceResult removeOrderService(Order order);
}
