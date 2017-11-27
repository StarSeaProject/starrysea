package top.starrysea.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.view.in.OnlineForAdd;

public interface IMailController {

	ModelAndView addMailController(OnlineForAdd online, BindingResult bindingResult);
}
