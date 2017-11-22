package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.dao.IWorkDao;
import top.starrysea.entity.Work;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class WorkDaoTest {

	@Autowired
	private IWorkDao workDao;

	@Test
	public void getAllWorkDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(workDao.getAllWorkDao(condition, new Work.Builder().build()).getResult());
	}

	@Test
	public void getWorkCountDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(workDao.getWorkCountDao(condition, new Work.Builder().build()).getResult());
	}

	@Test
	public void getWorkDao() {
		System.out.println(workDao.getWorkDao(new Work.Builder().workId(1).build()).getResult());
	}

	@Test
	public void saveWorkDao() {
		System.out.println(workDao.saveWorkDao(new Work.Builder().workName("啊啊啊").workPdfpath("dd").workStock(4)
				.workUploadTime(Common.getNowDate()).build()));
	}

	@Test
	public void deleteWorkDao() {
		System.out.println(workDao.deleteWorkDao(new Work.Builder().workId(5).build()));
	}

	@Test
	public void updateWorkStockDao() {
		System.out.println(workDao.updateWorkStockDao(new Work.Builder().workId(3).workStock(1).build()));
	}

	@Test
	public void getStockDao() {
		System.out.println(workDao.getStockDao(new Work.Builder().workId(1).build()).getResult());
	}
}
