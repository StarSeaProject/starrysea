package top.starrysea.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.ModelAndViewFactory;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IOnlineController;
import top.starrysea.object.view.in.OnlineForAdd;
import top.starrysea.service.IOnlineService;

@Controller
@RequestMapping("/online")
public class OnlineControllerImpl implements IOnlineController {

	@Autowired
	private IOnlineService onlineService;

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addOnlineController(OnlineForAdd online, BindingResult bindingResult, Device device) {
		ServiceResult serviceResult = onlineService.addMailService(online.toDTO());
		if (!serviceResult.isSuccessed()) {
			return ModelAndViewFactory.newErrorMav(serviceResult.getErrInfo(), device);
		}
		// 添加成功则返回成功页面
		return ModelAndViewFactory.newSuccessMav("订阅成功", device);
	}

}
