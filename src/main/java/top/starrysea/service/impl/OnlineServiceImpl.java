package top.starrysea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.common.Common;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IOnlineDao;
import top.starrysea.object.dto.Online;
import top.starrysea.service.IOnlineService;

@Service("onlineService")
public class OnlineServiceImpl implements IOnlineService {

	@Autowired
	private IOnlineDao onlineDao;

	@Override
	public ServiceResult addMailService(Online online) {
		online.setOnlineId(Common.getCharId("O-", 10));
		DaoResult daoResult = onlineDao.saveOnlineDao(online);
		ServiceResult sr = new ServiceResult();
		sr.setSuccessed(daoResult.isSuccessed());
		if (!daoResult.isSuccessed())
			sr.setErrInfo(daoResult.getErrInfo());
		return sr;
	}

}
