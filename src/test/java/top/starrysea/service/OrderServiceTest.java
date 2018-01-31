package top.starrysea.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
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
		System.out.println(orderService.queryAllOrderService(condition, new Orders.Builder().orderName("啊啊啊").build()));
	}

	@Test
	public void queryOrderService() {
		System.out.println(orderService
				.queryOrderService(new Orders.Builder().orderNum("MWsW8VnJZSWLX7WdzXJDYpVKMsKzZD").build()));
	}

//	@Test
//	public void addOrderService() {
//		System.out.println(orderService.addOrderService(new Orders.Builder().work(new Work.Builder().workId(6).build())
//				.orderName("liuyang").orderArea(new Area.Builder().areaId(1).build()).orderAddress("asdasdasd")
//				.orderEMail("709782571@qq.com").build()));
//	}
//
//	@Test
//	public void modifyOrderService() {
//		System.out.println(orderService.modifyOrderService(new Orders.Builder().orderId("O-095GgIDt")
//				.orderStatus((short) 2).orderExpressnum("asdaqweqwesd").build()));
//	}
	
	@Test
	public void exportOrderToXls() {
		orderService.exportOrderToXlsService();
	}
	
	@Test
	public void queryAllProvinceService() {
		orderService.queryAllProvinceService();
	}
}
