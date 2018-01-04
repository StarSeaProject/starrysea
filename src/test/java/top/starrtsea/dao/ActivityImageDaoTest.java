package top.starrtsea.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.dao.IActivityImageDao;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class ActivityImageDaoTest {

	@Autowired
	private IActivityImageDao dao;
	
	@Test
	public void getAllActivityImageDao() {
		System.out.println(dao.getAllActivityImageDao(new Activity.Builder().build()).getResult(List.class));
	}

	@Test
	public void saveActivityImageDao() {
		List<ActivityImage> list=new ArrayList<>();
		Activity activity=new Activity.Builder().activityId(447).build();
		list.add(new ActivityImage.Builder().activity(activity).activityImagePath("/asdasd").build());
		list.add(new ActivityImage.Builder().activity(activity).activityImagePath("/asdasd").build());
		list.add(new ActivityImage.Builder().activity(activity).activityImagePath("/asdasd").build());
		System.out.println(dao.saveActivityImageDao(list).isSuccessed());
	}
}
