package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IActivityDao;
import top.starrysea.object.dto.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class ActivityDaoTest {
	
	@Autowired
	private IActivityDao activityDao;

	@Test
	public void addActivityDao() {
		DaoResult result = activityDao.saveActivityDao(new Activity.Builder().activityName("一起退会1")
				.activityContent("鱼死网破今晚就走").activityStatus((short) 0).build());
		System.out.println(result.getErrInfo());
	}

	@Test
	public void updateActivityDao() {
		DaoResult result = activityDao
				.updateActivityDao(new Activity.Builder().activityStatus((short) 2).activityId(1).build());
		System.out.println(result.getErrInfo());
	}

	@Test
	public void getAllActivityDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		DaoResult result = activityDao.getAllActivityDao(condition, new Activity.Builder().activityName("一起").build());
		System.out.println(result.getResult());
	}

	@Test
	public void getActivityCountDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		DaoResult result = activityDao.getActivityCountDao(condition, new Activity.Builder().activityName("一起").build());
		System.out.println(result.getResult());
	}

	@Test
	public void getActivityDao() {
		System.out.println(activityDao.getActivityDao(new Activity.Builder().activityId(2).build()).getResult());
	}

	@Test
	public void deleteActivityDao() {
		System.out.println(activityDao.deleteActivityDao(new Activity.Builder().activityId(2).build()).getErrInfo());
	}
}
