package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import top.starrysea.common.Condition;
import top.starrysea.entity.Orders;

public interface IOrderController {

	Model getAllOrderController(Condition condition, Orders order);

	Model getOrderController(Orders order);

	Model addOrderController(Orders order);

	Model modifyOrderController(HttpSession session, Orders order);

	Model removeOrderController(HttpSession session, Orders order);
}
