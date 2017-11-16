package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.dao.IWorkDao;
import top.starrysea.entity.Work;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=StarrtseaApplication.class)
public class WorkDaoTest {

	@Autowired
	private IWorkDao workDao;
	
	@Test
	public void getAllWorkDao() {
		Condition condition=new Condition();
		condition.setPage(1);
		System.out.println(workDao.getAllWorkDao(condition, new Work()));
	}

}
