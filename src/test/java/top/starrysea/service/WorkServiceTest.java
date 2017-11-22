package top.starrysea.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Common;
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
		condition.setPage(1);
		System.out
				.println(workService.queryAllWorkService(condition, new Work.Builder().build()));
	}

	@Test
	public void queryWorkService() {
		System.out.println(workService.queryWorkService(new Work.Builder().workId(6).build()));
	}
	@Test
	public void addWorkService(){
		try {
			MockMultipartFile file=new MockMultipartFile("5.pdf", new FileInputStream(new File("F://jetty-distribution-9.4.7.v20170914.zip")));
			System.out.println(workService.addWorkService(file, new Work.Builder().workName("a").workStock(5).workUploadTime(Common.getNowDate()).build()));
		}catch (Exception e) {
			System.out.println("错错错");
		}
		}
}
