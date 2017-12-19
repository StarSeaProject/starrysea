package top.starrysea.controller;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.view.in.WorkForAdd;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;

public interface IWorkController {

	Map<String, Object> queryAllWorkControllerAjax(WorkForAll work);

	ModelAndView queryWorkController(WorkForOne work, BindingResult bindingResult);

	Map<String, Object> queryWorkControllerAjax(WorkForOne work, BindingResult bindingResult);

	ModelAndView addWorkController(MultipartFile coverFile, MultipartFile[] imageFiles, WorkForAdd work,
			BindingResult bindingResult);

	ModelAndView removeWorkController(WorkForOne work, BindingResult bindingResult);
}
