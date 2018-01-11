package top.starrysea.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
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
		condition.setPage(2);
		System.out.println(service.queryAllActivityService(condition, new Activity.Builder().activityName("qweqwe").build()));
	}

	@Test
	public void queryActivityService() {
		ServiceResult serviceResult=service.queryActivityService(new Activity.Builder().activityId(190).build());
		System.out.println(serviceResult.getResult(List.class));
		System.out.println(serviceResult.getResult(Activity.class));
	}

//	@Test
//	public void addActivityService() {
//		Activity activity = new Activity.Builder().activityName("asd").activityStatus((short) 1)
//				.activitySummary("qweqwe").activityContent("21qw2e1qwe").build();
//		try {
//			MockMultipartFile coverFile = new MockMultipartFile("1.jpg",
//					new FileInputStream(new File("D:/develop/nginx-1.12.1/img/starsea.png")));
//			service.addActivityService(coverFile, activity, new ArrayList<>());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
