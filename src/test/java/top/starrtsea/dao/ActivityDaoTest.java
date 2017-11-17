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
import top.starrysea.entity.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class ActivityDaoTest {
	@Autowired
	private IActivityDao activityDao;

	public void addActivityDao() {
		DaoResult result = activityDao.addActivityDao(new Activity.Builder().activityName("一起退会1")
				.activityContent("鱼死网破今晚就走").activityStatus((short) 0).activityQrcode("/starrysea").build());
		System.out.println(result.getErrInfo());
	}

	public void updateActivityDao() {
		DaoResult result = activityDao
				.updateActivityDao(new Activity.Builder().activityStatus((short) 2).activityId(4).build());
		System.out.println(result.getErrInfo());
	}

	public void getAllActivityDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		DaoResult result = activityDao.getAllActivityDao(condition, new Activity.Builder().build());
		System.out.println(result.getResult());
	}

	public void getActivityCountDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		DaoResult result = activityDao.getActivityCountDao(condition, new Activity.Builder().build());
		System.out.println(result.getResult());
	}

	public void getActivityDao() {
		System.out.println(activityDao.getActivityDao(new Activity.Builder().activityId(4).build()).getResult());
	}

	@Test
	public void deleteActivityDao() {
		System.out.println(activityDao.deleteActivityDao(new Activity.Builder().activityId(4).build()).getErrInfo());
	}
}
