package top.starrysea.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.object.view.in.ActivityForAdd;
import top.starrysea.object.view.in.ActivityForAll;
import top.starrysea.object.view.in.ActivityForModify;
import top.starrysea.object.view.in.ActivityForOne;
import top.starrysea.object.view.in.ActivityImageForAdd;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class ActivityControllerTest {

	@Autowired
	private IActivityController controller;

	@Test
	public void queryAllActivityController() {
		Condition condition = new Condition();
		condition.setPage(1);
		ActivityForAll activity = new ActivityForAll();
		ModelAndView modelAndView = controller.queryAllActivityController(condition, activity);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void queryActivityController() {
		ActivityForOne activity = new ActivityForOne();
		ModelAndView modelAndView = controller.queryActivityController(activity, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void addActivityController() {
		HttpSession session = new MockHttpSession();
		session.setAttribute("adminId", 1);
		ActivityForAdd activity = new ActivityForAdd();
		activity.setActivityName("asdfasdf");
		activity.setActivityContent("qweasdqaweqweasd");
		List<ActivityImageForAdd> activityImages = new ArrayList<>();
		ActivityImageForAdd activityImage = new ActivityImageForAdd();
		activityImage.setActivityImagePath("aa");
		activityImages.add(activityImage);
		activityImage = new ActivityImageForAdd();
		activityImage.setActivityImagePath("bb");
		activityImages.add(activityImage);
		activity.setActivityImages(activityImages);
		ModelAndView modelAndView = controller.addActivityController(session, null, activity, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void modifyActivityController() {
		HttpSession session = new MockHttpSession();
		session.setAttribute("adminId", 1);
		ActivityForModify activity = new ActivityForModify();
		ModelAndView modelAndView = controller.modifyActivityController(session, activity, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void removeActivityController() {
		HttpSession session = new MockHttpSession();
		session.setAttribute("adminId", 1);
		ActivityForOne activity = new ActivityForOne();
		ModelAndView modelAndView = controller.removeActivityController(session, activity, null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
