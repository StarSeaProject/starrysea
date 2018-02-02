package top.starrysea.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IOnlineController;
import top.starrysea.object.view.in.OnlineForAdd;
import top.starrysea.service.IOnlineService;

import static top.starrysea.common.Const.*;

@Controller
@RequestMapping("/online")
public class OnlineControllerImpl implements IOnlineController {

	@Autowired
	private IOnlineService onlineService;

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addOnlineController(OnlineForAdd online, BindingResult bindingResult, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = onlineService.addMailService(online.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			// 查询失败则返回错误页面
			modelAndView.setViewName(device.isMobile() ? MOBILE + ERROR_VIEW : ERROR_VIEW);
			return modelAndView;
		}
		// 添加成功则返回成功页面
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

}
