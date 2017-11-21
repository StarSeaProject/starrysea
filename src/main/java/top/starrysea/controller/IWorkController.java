package top.starrysea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.entity.Work;

public interface IWorkController {

	Model queryAllWorkController(Condition condition, Work work);

	Model queryWorkController(Work work);

	Model addWorkController(HttpSession session, CommonsMultipartFile file, Work work);

	Model removeWorkController(HttpSession session, Work work);
}
