package top.starrysea.service;

import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Online;
import top.starrysea.object.dto.Work;

public interface IMailService {

	ServiceResult addMailService(Online online);
	
	void sendMailService(Work work);
}
