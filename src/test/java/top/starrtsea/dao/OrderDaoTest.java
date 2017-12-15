package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import groovyjarjarantlr.collections.List;
import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.dao.IOrderDao;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class OrderDaoTest {

	@Autowired
	private IOrderDao orderDao;

	@Test
	public void getOrderDao() {
		System.out.println(orderDao.getOrderDao(new Orders.Builder().orderNum("111").build()).getResult(Orders.class));
	}

	@Test
	public void saveOrderDao() {
		orderDao.saveOrderDao(new Orders.Builder().orderName("xjl").orderArea(new Area.Builder().areaId(1).build())
				.orderAddress("asdasdasd").work(new Work.Builder().workId(6).build()).build());
	}

	@Test
	public void updateOrderDao() {
		orderDao.updateOrderDao(new Orders.Builder().orderId("O-b70AiZDc").orderStatus((short) 3).build());
	}

	@Test
	public void deleteOrderDao() {
		orderDao.deleteOrderDao(new Orders.Builder().orderId("O-b70AiZDc").build());
	}

	@Test
	public void getAllOrderDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(orderDao.getAllOrderDao(condition, new Orders.Builder().orderStatus((short) 1).build())
				.getResult(List.class));
	}

	@Test
	public void getOrderCountDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(orderDao.getOrderCountDao(condition, new Orders.Builder().orderStatus((short) 1).build())
				.getResult(Integer.class));
	}
}
