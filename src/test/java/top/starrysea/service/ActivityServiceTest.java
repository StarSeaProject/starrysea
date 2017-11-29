package top.starrysea.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.object.dto.Activity;
import top.starrysea.service.IActivityService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class ActivityServiceTest {

	@Autowired
	private IActivityService service;
	
	@Test
	public void queryAllActivityService() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(service.queryAllActivityService(condition, new Activity.Builder().activityName("一起").build()));
	}
	
	@Test
	public void queryActivityService() {
		System.out.println(service.queryActivityService(new Activity.Builder().activityId(15).build()));
	}

}
