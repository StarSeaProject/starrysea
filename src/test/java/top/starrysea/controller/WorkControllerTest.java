package top.starrysea.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.Device;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarryseaApplication;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class WorkControllerTest {
	
	@Autowired
	private IWorkController workController;

	@Test
	public void queryAllWorkController() {
		WorkForAll work = new WorkForAll();
		Device device = null;
		work.setPage(1);
		ModelAndView modelAndView = workController.queryAllWorkController(work, device);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
	
	@Test
	public void queryWorkController() {
		ModelAndView modelAndView = workController.queryWorkController(new WorkForOne(), null,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void removeWorkController() {
		WorkForOne work = new WorkForOne();
		work.setWorkId(1);
		System.out.println(workController.removeWorkController(work, null,null));
	}
}
