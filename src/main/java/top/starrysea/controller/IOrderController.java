package top.starrysea.controller;

import org.springframework.ui.Model;

import top.starrysea.entity.Order;

public interface IOrderController {
	Model getOrderController(Order order);

	Model addOrderController(Order order);

	Model modifyOrderController(Order order);

	Model removeOrderController(Order order);
}
