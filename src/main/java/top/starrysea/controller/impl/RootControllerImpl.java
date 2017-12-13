package top.starrysea.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping("/intro")
	public ModelAndView intro() {
		return new ModelAndView("intro");
	}

	@RequestMapping("/admin")
	public ModelAndView admin() {
		return new ModelAndView("admin_login");
	}

	@Override
	@RequestMapping("/uploads")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("myFileName") MultipartFile file) {
		try {
			fileUtil.saveFile(file, FileCondition.of(FileType.IMG, ""));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		Map<String, Object> result = new HashMap<>();
		List<String> data = new ArrayList<>();
		data.add("result.png");
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
