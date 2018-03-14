package top.starrysea.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.StarrtseaApplication;
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
		ActivityForAll activity = new ActivityForAll();
		activity.setPage(1);
		ModelAndView modelAndView = controller.queryAllActivityController(activity,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
	
	@Test
	public void queryActivityController() {
		ActivityForOne activity = new ActivityForOne();
		ModelAndView modelAndView = controller.queryActivityController(activity, null,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void addActivityController() {
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
		ModelAndView modelAndView = controller.addActivityController(null, activity, null,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void modifyActivityController() {
		ActivityForModify activity = new ActivityForModify();
		ModelAndView modelAndView = controller.modifyActivityController(activity, null,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}

	@Test
	public void removeActivityController() {
		ActivityForOne activity = new ActivityForOne();
		ModelAndView modelAndView = controller.removeActivityController(activity, null,null);
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
	}
}
