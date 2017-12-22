package top.starrysea.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.Device;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class WorkControllerTest {
	
	@Autowired
	private IWorkController workController;

	@Test
	public void queryAllWorkController() {
		Condition condition = new Condition();
		condition.setPage(1);
		WorkForAll work = new WorkForAll();
		Device device = null;
		ModelAndView modelAndView = workController.queryAllWorkController(condition, work, device);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
	
	@Test
	public void queryWorkController() {
		ModelAndView modelAndView = workController.queryWorkController(new WorkForOne(), null,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

//	@Test
//	public void addWorkController() {
//		HttpSession session = new MockHttpSession();
//		session.setAttribute("adminId", 1);
//		WorkForAdd work = new WorkForAdd();
//		MockMultipartFile file;
//		try {
//			file = new MockMultipartFile("1.txt", new FileInputStream(new File("D://1.txt")));
//			ModelAndView modelAndView = workController.addWorkController(session, file, work, null);
//			System.out.println(modelAndView.getViewName());
//			System.out.println(modelAndView.getModel());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void removeWorkController() {
		WorkForOne work = new WorkForOne();
		work.setWorkId(1);
		System.out.println(workController.removeWorkController(work, null,null));
	}
}
