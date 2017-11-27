package top.starrysea.dao;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Online;

public interface IOnlineDao {

	DaoResult getAllOnlineDao();
	
	DaoResult saveOnlineDao(Online online);
}
