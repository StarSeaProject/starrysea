package top.starrysea.aspect;

import java.util.Arrays;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class ParamsAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* top.starrysea.controller.impl.*.*(..))")
	public Object paramsLog(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		if (logger.isDebugEnabled()) {
			logger.debug(className + "." + methodName + "() 前端入参:" + Arrays.toString(pjp.getArgs()));
		}
		Object result = pjp.proceed();
		if (logger.isDebugEnabled()) {
			String outMessage = className + "." + methodName + "() 后台出参:";
			if (result instanceof ModelAndView) {
				logger.debug(outMessage + ((ModelAndView) result).getModel());
			}
			if (result instanceof Map) {
				logger.debug(outMessage + result);
			}
		}
		return result;
	}
}
