package top.starrtsea.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarryseaApplication;
import top.starrysea.dao.IWorkTypeDao;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class WorkTypeDaoTest {

	@Autowired
	private IWorkTypeDao dao;
	
	@Test
	public void saveWorkTypeDao() {
		List<WorkType> workTypes=new ArrayList<>();
		Work work=new Work.Builder().workId(6).build();
		workTypes.add(new WorkType.Builder().work(work).name("aaa").stock(111).build());
		workTypes.add(new WorkType.Builder().work(work).name("bbb").stock(222).build());
		workTypes.add(new WorkType.Builder().work(work).name("ccc").stock(333).build());
		dao.saveWorkTypeDao(workTypes);
	}

	@Test
	public void getAllWorkTypeDao() {
		System.out.println(dao.getAllWorkTypeDao(new WorkType.Builder().work(new Work.Builder().workId(6).build()).build()).getResult(List.class));
	}
	
	@Test
	public void updateWorkTypeStockDao() {
		dao.updateWorkTypeStockDao(new WorkType.Builder().workTypeId(1).stock(100).build());
	}
	
	@Test
	public void reduceWorkTypeStockDao() {
		dao.reduceWorkTypeStockDao(new WorkType.Builder().workTypeId(1).stock(100).build());
	}
	
	@Test
	public void deleteWorkTypeDao() {
		dao.deleteWorkTypeDao(new WorkType.Builder().workTypeId(4).build());
	}
	
	@Test
	public void getAllWorkTypeForShoppingCarDao() {
		List<WorkType> workTypes=new ArrayList<>();
		workTypes.add(new WorkType.Builder().workTypeId(1).build());
		workTypes.add(new WorkType.Builder().workTypeId(2).build());
		System.out.println(dao.getAllWorkTypeForShoppingCarDao(workTypes).getTheResult());
	}
}
