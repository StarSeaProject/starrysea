package top.starrysea.controller.impl;

import static top.starrysea.common.Const.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.starrysea.controller.IRootController;
import top.starrysea.file.FileCondition;
import top.starrysea.file.FileType;
import top.starrysea.file.FileUtil;

@Controller
public class RootControllerImpl implements IRootController {

	@Autowired
	private FileUtil fileUtil;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@RequestMapping("/")
	public ModelAndView index(Device device) {
		return new ModelAndView(device.isMobile() ?MOBILE +  "index" : "index");
	}

	@RequestMapping("/intro")
	public ModelAndView intro() {
		return new ModelAndView("intro");
	}

	@RequestMapping("/admin")
	public ModelAndView admin(HttpSession session, Device device) {
		if (session.getAttribute(ADMIN_SESSION_KEY) != null) {
			return new ModelAndView(device.isMobile() ? MOBILE + BOSS : BOSS);
		}
		return new ModelAndView("admin_login");
	}

	@Override
	@RequestMapping("/uploads")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		String filePath = null;
		try {
			filePath = fileUtil.saveFile(file, FileCondition.of(FileType.IMG, ""));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		Map<String, Object> result = new HashMap<>();
		List<String> data = new ArrayList<>();
		data.add(filePath);
		result.put("errno", 0);
		result.put("data", data);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String theResult = mapper.writeValueAsString(result);
			response.getWriter().write(theResult);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
