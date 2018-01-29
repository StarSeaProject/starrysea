package top.starrysea.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static top.starrysea.common.Const.INFO;
import static top.starrysea.common.Const.SUCCESS_VIEW;

public class ErrorInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (response.getStatus() == 404) {
			modelAndView.setViewName(SUCCESS_VIEW);
			modelAndView.addObject(INFO, "找不到该页面哦");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 什么都不做
	}

}
