package top.starrysea.dao;

import org.springframework.ui.Model;

import top.starrysea.common.DaoResult;
import top.starrysea.entity.Order;

public interface IOrderDao {
	Model getOrderDao(Order order);
	
	DaoResult addOrderDao(Order order);
	
	DaoResult modifyOrderDao(Order order);
	
	DaoResult removeOrderDao(Order order);
	
	
}
