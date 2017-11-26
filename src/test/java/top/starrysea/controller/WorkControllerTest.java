package top.starrysea.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.object.view.in.WorkForAdd;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class WorkControllerTest {
	@Autowired
	private IWorkController workController;
	@Test
	public void queryAllWorkController(){
		Condition condition=new Condition();
		condition.setPage(1);
		HttpSession session=new MockHttpSession();
		session.setAttribute("adminId", 1);
		WorkForAll work=new WorkForAll();
		ModelAndView modelAndView=workController.queryAllWorkController(condition, work);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
	@Test
	public void queryWorkController(){
		WorkForOne work=new WorkForOne();
		ModelAndView modelAndView=workController.queryWorkController(work, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
	@Test
	public void addWorkController(){
		HttpSession session=new MockHttpSession();
		session.setAttribute("adminId", 1);
		WorkForAdd work=new WorkForAdd();
		MockMultipartFile file;
		try {
			file = new MockMultipartFile("1.txt",new FileInputStream(new File("D://1.txt")));
			ModelAndView modelAndView=workController.addWorkController(session, file, work, null);
			System.out.println(modelAndView.getViewName());
			System.out.println(modelAndView.getModel());
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	@Test
	public void removeWorkController(){
		HttpSession session=new MockHttpSession();
		session.setAttribute("adminId", 1);
		WorkForOne work=new WorkForOne();
		work.setWorkId(1);
		ModelAndView modelAndView=workController.removeWorkController(session, work, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
