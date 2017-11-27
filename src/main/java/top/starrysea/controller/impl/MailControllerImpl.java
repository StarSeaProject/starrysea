package top.starrysea.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IMailController;
import top.starrysea.object.view.in.OnlineForAdd;
import top.starrysea.service.IMailService;

@Controller
public class MailControllerImpl implements IMailController {

	@Autowired
	private IMailService mailService;

	@Override
	public ModelAndView addMailController(OnlineForAdd online, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = mailService.addMailService(online.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject("errInfo", serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName("error");
			return modelAndView;
		}
		// 添加成功则返回成功页面
		modelAndView.setViewName("add_success");
		return modelAndView;
	}

}
