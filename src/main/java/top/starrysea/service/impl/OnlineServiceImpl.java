package top.starrysea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import top.starrysea.common.Common;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IOnlineDao;
import top.starrysea.object.dto.Online;
import top.starrysea.service.IOnlineService;

public class OnlineServiceImpl implements IOnlineService {

	@Autowired
	private IOnlineDao onlineDao;
	
	@Override
	public ServiceResult addMailService(Online online) {
		online.setOnlineId(Common.getCharId("O-", 10));
		return new ServiceResult(onlineDao.saveOnlineDao(online));
	}

}
