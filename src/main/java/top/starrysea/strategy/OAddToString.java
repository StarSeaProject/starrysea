package top.starrysea.strategy;

import top.starrysea.object.view.in.OrderForAdd;

/*
 * 具体策略算法
 */
public class OAddToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		OrderForAdd orderForAdd = (OrderForAdd) object;
		return "orderForAdd{\"orderAddress\":" + orderForAdd.getOrderAddress() + ",\"orderName\":"
				+ orderForAdd.getOrderName() + "}";
	}
}
