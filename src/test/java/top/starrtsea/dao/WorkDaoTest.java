package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarryseaApplication;
import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.dao.IWorkDao;
import top.starrysea.object.dto.Work;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class WorkDaoTest {

	@Autowired
	private IWorkDao workDao;

	@Test
	public void getAllWorkDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(workDao.getAllWorkDao(condition, new Work.Builder().build()).getResult(List.class));
	}

	@Test
	public void getWorkCountDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(workDao.getWorkCountDao(condition, new Work.Builder().build()).getResult(Work.class));
	}

	@Test
	public void getWorkDao() {
		System.out.println(workDao.getWorkDao(new Work.Builder().workId(6).build()).getResult(Integer.class));
	}

	@Test
	public void saveWorkDao() {
		System.out.println(workDao.saveWorkDao(
				new Work.Builder().workName("啊啊啊").workPdfpath("dd").workUploadTime(Common.getNowDate()).build()));
	}

	@Test
	public void deleteWorkDao() {
		System.out.println(workDao.deleteWorkDao(new Work.Builder().workId(5).build()));
	}
}
