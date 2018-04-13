package top.starrtsea.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarryseaApplication;
import top.starrysea.dao.IProvinceDao;
import top.starrysea.object.view.out.ProvinceForAddOrder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class AreaDaoTest {

	@Autowired
	private IProvinceDao dao;
	
	@Test
	public void getAllAreaDao() {
		List<ProvinceForAddOrder> list=dao.getAllProvinceDao().getResult(List.class);
		System.out.println(list);
	}

}
