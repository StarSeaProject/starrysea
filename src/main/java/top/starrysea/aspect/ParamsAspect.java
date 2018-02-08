package top.starrysea.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.strategy.ToStringContext;

@Component
@Aspect
public class ParamsAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* top.starrysea.controller.impl.*.*(..))")
	public Object paramsLog(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		List<String> list = new ArrayList<>();
		ToStringContext context = new ToStringContext();
		for (Object object : pjp.getArgs()) {
			list.add(context.toString(object));
		}
		if (logger.isDebugEnabled()) {
			logger.debug(className + "." + methodName + "() 前端入参:" + list);
		}
		Object result = pjp.proceed();
		if (logger.isDebugEnabled()) {
			String outMessage = className + "." + methodName + "() 后台出参:";
			if (result instanceof ModelAndView) {
				logger.debug(outMessage + context.toString(((ModelAndView) result).getModel()));
			}
			if (result instanceof Map) {
				logger.debug(outMessage + context.toString(result));
			}
		}
		return result;
	}
}
