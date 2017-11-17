package top.starrysea.controller;

import org.springframework.ui.Model;

import top.starrysea.entity.Orders;

public interface IOrderController {
	Model getOrderController(Orders order);

	Model addOrderController(Orders order);

	Model modifyOrderController(Orders order);

	Model removeOrderController(Orders order);
}
