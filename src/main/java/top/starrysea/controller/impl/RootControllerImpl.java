package top.starrysea.controller.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.starrysea.common.Common;
import top.starrysea.controller.IRootController;

@Controller
public class RootControllerImpl implements IRootController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final static String UPLOAD_PATH = "D:/develop/nginx-1.12.1/img/";

	@Override
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/intro")
	public ModelAndView intro() {
		return new ModelAndView("intro");
	}

	@Override
	@RequestMapping("/uploads")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("myFileName") MultipartFile file) {
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		File target = new File(UPLOAD_PATH);
		if (!target.exists())
			target.mkdirs();
		try {
			FileCopyUtils.copy(file.getInputStream(),
					new FileOutputStream(UPLOAD_PATH + Common.getCharId(5) + fileType));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
