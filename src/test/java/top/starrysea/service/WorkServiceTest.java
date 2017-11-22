package top.starry.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.entity.Work;
import top.starrysea.service.IWorkService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class WorkServiceTest {
	@Autowired
	private IWorkService workService;

	@Test
	public void queryAllWorkService() {
		Condition condition = new Condition();
		condition.setPage(2);
		System.out
				.println(workService.queryAllWorkService(condition, new Work.Builder().workId(13).build()).getResult());
	}

	@Test
	public void queryWorkService() {
		System.out.println(workService.queryWorkService(new Work.Builder().workId(13).build()).getResult());
	}
}
