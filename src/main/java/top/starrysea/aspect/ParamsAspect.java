package top.starrysea.aspect;

import java.util.Arrays;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class ParamsAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before(value="execution(* top.starrysea.controller.impl.*.*(..))")
	public void inParamsLog(JoinPoint jp) {
		if(logger.isDebugEnabled())
			logger.debug("前端入参:"+Arrays.toString(jp.getArgs()));
	}
	
	@AfterReturning(value="execution(org.springframework.web.servlet.ModelAndView top.starrysea.controller.impl.*.*(..))",returning = "modelAndView")
	public void outParamsLog(ModelAndView modelAndView) {
		if(logger.isDebugEnabled())
			logger.debug("后台出参:"+modelAndView.getModel());
	}
	
	@AfterReturning(value="execution(java.util.Map top.starrysea.controller.impl.*.*(..))",returning = "map")
	public void outParamsLog(Map<?,?> map) {
		if(logger.isDebugEnabled())
			logger.debug("后台出参:"+map);
	}
}
