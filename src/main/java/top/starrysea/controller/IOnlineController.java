package top.starrysea.controller;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.object.view.in.OnlineForAdd;

public interface IOnlineController {

	ModelAndView addOnlineController(OnlineForAdd online, BindingResult bindingResult, Device device);
}
