package top.starrysea.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.file.FileUtil;

import static top.starrysea.common.Const.CUCUIMG;
import static top.starrysea.common.Const.NOT_FOUND_VIEW;
import static top.starrysea.common.Const.HttpCode.*;

public class ErrorInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		int status = response.getStatus();
		if (status == NOT_FOUND || status == BAD_REQUEST || status == SERVER_ERROR) {
			modelAndView.setViewName(NOT_FOUND_VIEW);
			modelAndView.addObject(CUCUIMG, FileUtil.getCucuImg());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 什么都不做
	}

}
