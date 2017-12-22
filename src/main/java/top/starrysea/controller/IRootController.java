package top.starrysea.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface IRootController {

	ModelAndView index(Device device);

	void upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file);
	
}
