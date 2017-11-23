package top.starrysea.dao;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Admin;

public interface IAdminDao {
	DaoResult loginDao(Admin admin);
}
