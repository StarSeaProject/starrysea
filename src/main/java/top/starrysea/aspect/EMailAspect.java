package top.starrysea.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import top.starrysea.common.ServiceResult;
import top.starrysea.service.IMailService;

import static top.starrysea.common.ResultKey.*;

@Component
@Aspect
public class EMailAspect {

	@Resource(name = "workMailService")
	private IMailService workMailService;
	@Resource(name = "orderMailService")
	private IMailService orderMailService;
	@Resource(name = "sendOrderMailService")
	private IMailService sendOrderMailService;

	@AfterReturning(value = "execution(* top.starrysea.service.impl.WorkServiceImpl.addWorkService(..))", returning = "serviceResult")
	public void sendWorkEmail(ServiceResult serviceResult) {
		workMailService.sendMailService(serviceResult.getResult(WORK_DETAIL));
	}

	@AfterReturning(value = "execution(* top.starrysea.service.impl.OrderServiceImpl.addOrderService(..))", returning = "serviceResult")
	public void sendOrderEmail(ServiceResult serviceResult) {
		if(serviceResult.isSuccessed())
			orderMailService.sendMailService(serviceResult.getResult(ORDER_DETAIL));
	}

	@AfterReturning(value = "execution(* top.starrysea.service.impl.OrderServiceImpl.modifyOrderService(..))", returning = "serviceResult")
	public void sendSendOrderEmail(ServiceResult serviceResult) {
		sendOrderMailService.sendMailService(serviceResult.getResult(ORDER_DETAIL));
	}
}
