package top.starrysea.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import top.starrysea.common.ServiceResult;
import top.starrysea.object.bo.Blacklist;
import top.starrysea.object.dto.Orders;

@Component
@Aspect
@EnableConfigurationProperties({ Blacklist.class })
public class OrderBlacklistAspect {

	@Autowired
	private Blacklist blacklist;

	@Around("execution(* top.starrysea.service.impl.OrderServiceImpl.addOrderService(..))")
	public Object isPeopleInBlacklist(ProceedingJoinPoint pjp) throws Throwable {
		Orders order = (Orders) pjp.getArgs()[0];
		if (blacklist.getEmailList().contains(order.getOrderEMail())
				|| blacklist.getPhoneList().contains(order.getOrderPhone())) {
			ServiceResult sr = new ServiceResult(false);
			sr.setErrInfo("下单失败，请与管理员联系");
			return sr;
		}
		return pjp.proceed();
	}
}
