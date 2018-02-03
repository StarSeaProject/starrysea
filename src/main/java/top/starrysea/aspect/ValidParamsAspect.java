package top.starrysea.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import top.starrysea.common.Common;

@Component
@Aspect
public class ValidParamsAspect {

	@Around("execution(org.springframework.web.servlet.ModelAndView top.starrysea.controller.impl.*.*(..))")
	public Object vaildParams(ProceedingJoinPoint pjp) throws Throwable {
		for (Object param : pjp.getArgs()) {
			if (param instanceof BindingResult) {
				BindingResult bindingResult = BindingResult.class.cast(param);
				if (bindingResult.hasErrors()) {
					return Common.handleVaildError(bindingResult);
				}
				break;
			}
		}
		return pjp.proceed();
	}

	@Around("execution(java.util.Map top.starrysea.controller.impl.*.*(..))")
	public Object vaildParamsAjax(ProceedingJoinPoint pjp) throws Throwable {
		for (Object param : pjp.getArgs()) {
			if (param instanceof BindingResult) {
				BindingResult bindingResult = BindingResult.class.cast(param);
				if (bindingResult.hasErrors()) {
					return Common.handleVaildErrorForAjax(bindingResult);
				}
				break;
			}
		}
		return pjp.proceed();
	}
}
