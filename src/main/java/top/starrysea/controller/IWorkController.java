package top.starrysea.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.WorkForAdd;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;

public interface IWorkController {

	ModelAndView queryAllWorkController(Condition condition, WorkForAll work);

	Map<String, Object> queryAllWorkControllerAjax(WorkForAll work);

	ModelAndView queryWorkController(WorkForOne work, BindingResult bindingResult);

	Map<String, Object> queryWorkControllerAjax(WorkForOne work, BindingResult bindingResult);

	ModelAndView addWorkController(HttpSession session, MultipartFile file, WorkForAdd work,
			BindingResult bindingResult);

	ModelAndView removeWorkController(HttpSession session, WorkForOne work, BindingResult bindingResult);
}
