package top.starrtsea.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Common;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IOrderDetailDao;
import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class OrderDetailDaoTest {

	@Autowired
	private IOrderDetailDao dao;

	@Test
	public void getAllOrderDetail() {
		DaoResult result = dao.getAllOrderDetailDao(
				new OrderDetail.Builder().order(new Orders.Builder().orderId("O-0l0aGzF6").build()).build());
		System.out.println(result.getResult(List.class));
	}

	@Test
	public void saveOrderDetailsDao() {
		List<OrderDetail> orderDetails = new ArrayList<>();
		orderDetails.add(new OrderDetail.Builder().id(Common.getCharId(10))
				.workType(new WorkType.Builder().workTypeId(1).build())
				.order(new Orders.Builder().orderId("O-0l0aGzF6").build()).build());
		orderDetails.add(new OrderDetail.Builder().id(Common.getCharId(10))
				.workType(new WorkType.Builder().workTypeId(2).build())
				.order(new Orders.Builder().orderId("O-0l0aGzF6").build()).build());
		orderDetails.add(new OrderDetail.Builder().id(Common.getCharId(10))
				.workType(new WorkType.Builder().workTypeId(3).build())
				.order(new Orders.Builder().orderId("O-0l0aGzF6").build()).build());
		System.out.println(dao.saveOrderDetailsDao(orderDetails).isSuccessed());
	}

	@Test
	public void isOrderDetailExistDao() {
		System.out.println(dao.isOrderDetailExistDao(
				new OrderDetail.Builder().order(new Orders.Builder().orderPhone("17695483731").build())
						.workType(new WorkType.Builder().work(new Work.Builder().workId(44).build()).build()).build()).getResult(Boolean.class));
	}
	
	@Test
	public void getAllOrderDetailForXls() {
		System.out.println(dao.getAllOrderDetailForXls().getResult(List.class));
	}
}
