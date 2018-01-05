package top.starrysea.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Work;
import top.starrysea.service.IMailService;

@Component
@Aspect
public class EMailAspect {

	@Autowired
	private IMailService mailService;

	@AfterReturning(value = "execution(* top.starrysea.service.impl.WorkServiceImpl.addWorkService(..))", returning = "serviceResult")
	public void sendEmail(ServiceResult serviceResult) {
		mailService.sendMailService(serviceResult.getResult(Work.class));
	}
}
