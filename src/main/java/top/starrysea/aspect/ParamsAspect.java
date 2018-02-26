package top.starrysea.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.params.handler.ParamsHandlers;

@Component
@Aspect
public class ParamsAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* top.starrysea.controller.impl.*.*(..))")
	public Object paramsLog(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		List<String> list = new ArrayList<>();
		Function<Object, Object> handler = ParamsHandlers.createHandler();
		for (Object object : pjp.getArgs()) {
			list.add((String)handler.apply(object));
		}
		if (logger.isDebugEnabled()) {
			logger.debug(className + "." + methodName + "() 前端入参:" + list);
		}
		Object result = pjp.proceed();
		if (logger.isDebugEnabled()) {
			String outMessage = className + "." + methodName + "() 后台出参:";
			if (result instanceof ModelAndView) {
				logger.debug(outMessage + handler.apply(((ModelAndView) result).getModel()));
			}
			if (result instanceof Map) {
				logger.debug(outMessage + handler.apply(result));
			}
		}
		return result;
	}
}
