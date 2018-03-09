package top.starrysea.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterThrowing(throwing = "ex", pointcut = "execution(* top.starrysea.*.*.*(..))")
	public void afterThrowException(JoinPoint jp, Exception ex) {
		String msg = jp.getTarget().getClass().getName() + "发生了异常";
		logger.error(msg, ex);
	}
}
