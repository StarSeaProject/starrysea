package top.starrtsea.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarryseaApplication;
import top.starrysea.dao.IActivityImageDao;
import top.starrysea.object.dto.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class ActivityImageDaoTest {

	@Autowired
	private IActivityImageDao dao;
	
	@Test
	public void getAllActivityImageDao() {
		System.out.println(dao.getAllActivityImageDao(new Activity.Builder().build()).getResult(List.class));
	}

}
