package top.starrysea.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;
import top.starrysea.object.dto.WorkType;
import top.starrysea.service.IWorkService;

import static top.starrysea.common.ResultKey.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class WorkServiceTest {
	@Autowired
	private IWorkService workService;

	@Test
	public void queryAllWorkService() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(workService.queryAllWorkService(condition, new Work.Builder().workName("qweqwe").build()));
	}

	@Test
	public void queryWorkService() {
		ServiceResult serviceResult = workService.queryWorkService(new Work.Builder().workId(6).build());
		Work w=serviceResult.getResult(WORK);
		List<WorkImage> wis=serviceResult.getResult(LIST_1);
		List<WorkType> wts=serviceResult.getResult(LIST_2);
		System.out.println(w);
		System.out.println(wis);
		System.out.println(wts);
	}

//	@Test
//	public void addWorkService() {
//		Work work = new Work.Builder().workName("a").workStock(5).workUploadTime(Common.getNowDate()).workCover("aaa")
//				.workPdfpath("aaa").workSummary("asdasdasd").build();
//		try {
//			MockMultipartFile coverFile = new MockMultipartFile("1.jpg",
//					new FileInputStream(new File("D:/develop/nginx-1.12.1/img/starsea.png")));
//			MockMultipartFile[] array = new MockMultipartFile[1];
//			System.out.println(workService.addWorkService(coverFile, array, work));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
