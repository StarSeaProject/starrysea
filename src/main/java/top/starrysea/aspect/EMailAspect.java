package top.starrysea.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.service.IMailService;

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
		workMailService.sendMailService(serviceResult.getResult(Work.class));
	}

	@AfterReturning(value = "execution(* top.starrysea.service.impl.OrderServiceImpl.addOrderService(..))", returning = "serviceResult")
	public void sendOrderEmail(ServiceResult serviceResult) {
		orderMailService.sendMailService(serviceResult.getResult(Orders.class));
	}

	@AfterReturning(value = "execution(* top.starrysea.service.impl.OrderServiceImpl.modifyOrderService(..))", returning = "serviceResult")
	public void sendSendOrderEmail(ServiceResult serviceResult) {
		sendOrderMailService.sendMailService(serviceResult.getResult(Orders.class));
	}
}
