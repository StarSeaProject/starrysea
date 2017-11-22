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
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.starrysea.controller.IUserController;
import top.starrysea.entity.Admin;

@Controller
public class UserControllerImpl implements IUserController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/uploads")
    public void uploads(HttpServletRequest request,HttpServletResponse response, @RequestParam("myFileName") MultipartFile file){
        String path = request.getServletContext().getRealPath("/image");
        File target = new File(path);
        if (!target.exists())
        	target.mkdirs();
        try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(path+"/result.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        Map<String,Object> result=new HashMap<>();
        List<String> data=new ArrayList<>();
        data.add("image/result.png");
        result.put("errno", 0);
        result.put("data", data);
        ObjectMapper mapper=new ObjectMapper();
        try {
        	String theResult=mapper.writeValueAsString(result);
			response.getWriter().write(theResult);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	// 管理员登陆
	public Model loginController(HttpSession session, Admin admin) {
		// TODO 自动生成的方法存根
		return null;
	}

}
