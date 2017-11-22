package top.starry.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.entity.Area;
import top.starrysea.entity.Orders;
import top.starrysea.entity.Work;
import top.starrysea.service.IOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class OrderServiceTest {
	@Autowired
	private IOrderService orderService;

	@Test
	public void queryAllOrderSerivce() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(orderService.queryAllOrderService(condition, new Orders.Builder().orderName("啊啊啊").build())
				.getResult());
	}

	@Test
	public void queryOrderService() {
		System.out.print(orderService
				.queryOrderService(new Orders.Builder().orderNum("7pQVf9IZzRGzbGnxE2QlnVqoJm6CCo").build()));
	}

	@Test
	public void addOrderService() {
		System.out.println(orderService.addOrderService(new Orders.Builder().orderName("liuyang")
				.orderArea(new Area.Builder().areaId(1).build()).orderAddress("asdasdasd").build(),
				new Work.Builder().workStock(1).workId(13).build()).getErrInfo());
	}
}
