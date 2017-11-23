package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.dto.Work;

public interface IWorkController {

	ModelAndView queryAllWorkController(Condition condition, Work work);

	ModelAndView queryWorkController(Work work);

	ModelAndView addWorkController(HttpSession session, MultipartFile file, Work work);

	ModelAndView removeWorkController(HttpSession session, Work work);
}
