package top.starrysea.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IMailController;
import top.starrysea.object.view.in.OnlineForAdd;
import top.starrysea.service.IOnlineService;

import static top.starrysea.common.Const.*;

@Controller
public class MailControllerImpl implements IMailController {

	@Autowired
	private IOnlineService onlineService;

	@Override
	public ModelAndView addMailController(OnlineForAdd online, BindingResult bindingResult, Device device) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = onlineService.addMailService(online.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName(device.isNormal() ? ERROR_VIEW : MOBILE + ERROR_VIEW);
			return modelAndView;
		}
		// 添加成功则返回成功页面
		modelAndView.setViewName(device.isNormal() ? SUCCESS_VIEW : MOBILE + SUCCESS_VIEW);
		return modelAndView;
	}

}
