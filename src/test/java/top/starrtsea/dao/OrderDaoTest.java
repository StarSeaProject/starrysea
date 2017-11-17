package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.dao.IOrderDao;
import top.starrysea.entity.Area;
import top.starrysea.entity.Orders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class OrderDaoTest {

	@Autowired
	private IOrderDao orderDao;

	@Test
	public void getOrderDao() {
		System.out.println(orderDao.getOrderDao(new Orders.Builder().orderNum("111").build()).getResult());
	}

	@Test
	public void saveOrderDao() {
		orderDao.saveOrderDao(new Orders.Builder().orderName("xjl").orderArea(new Area.Builder().areaId(1).build())
				.orderAddress("asdasdasd").build());
	}

	@Test
	public void updateOrderDao() {
		orderDao.updateOrderDao(new Orders.Builder().orderId("O-b70AiZDc").orderStatus((short)3).build());
	}
	
	@Test
	public void deleteOrderDao() {
		orderDao.deleteOrderDao(new Orders.Builder().orderId("O-b70AiZDc").build());
	}
}
