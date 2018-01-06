package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IActivityDao;
import top.starrysea.object.dto.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class ActivityDaoTest {

	@Autowired
	private IActivityDao activityDao;

//	@Test
//	public void saveActivityDao() {
//		DaoResult result = activityDao.saveActivityDao(new Activity.Builder().activityName("一起退会1")
//				.activityContent("鱼死网破今晚就走").activityStatus((short) 0).activityCover("/aa.jpg").activitySummary("asdasdasd").build());
//		System.out.println(result);
//	}
//
//	@Test
//	public void updateActivityDao() {
//		DaoResult result = activityDao
//				.updateActivityDao(new Activity.Builder().activityStatus((short) 3).activityEndtime(Common.getNowDate()).activityId(448).build());
//		System.out.println(result);
//	}

	@Test
	public void getAllActivityDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		DaoResult result = activityDao.getAllActivityDao(condition, new Activity.Builder().build());
		System.out.println(result.getResult(List.class));
	}

	@Test
	public void getActivityCountDao() {
		Condition condition = new Condition();
		condition.setPage(1);
		DaoResult result = activityDao.getActivityCountDao(condition,
				new Activity.Builder().build());
		System.out.println(result.getResult(Integer.class));
	}

	@Test
	public void getActivityDao() {
		System.out.println(
				activityDao.getActivityDao(new Activity.Builder().activityId(448).build()).getResult(Activity.class));
	}

//	@Test
//	public void deleteActivityDao() {
//		System.out.println(activityDao.deleteActivityDao(new Activity.Builder().activityId(448).build()));
//	}
//	
//	@Test
//	public void updateAddActivityMoneyDao() {
//		List<Activity> list=new ArrayList<>();
//		list.add(new Activity.Builder().activityMoney(100d).activityId(447).build());
//		list.add(new Activity.Builder().activityMoney(100d).activityId(447).build());
//		list.add(new Activity.Builder().activityMoney(100d).activityId(447).build());
//		System.out.println(activityDao.updateAddActivityMoneyDao(list));
//	}
//	
//	@Test
//	public void updateReduceActivityMoneyDao() {
//		System.out.println(activityDao.updateReduceActivityMoneyDao(new Activity.Builder().activityMoney(100d).activityId(447).build()));
//	}
}
