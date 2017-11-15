package top.starrysea.service;

import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Admin;

public interface IUserService {
	ServiceResult loginService(Admin admin);
}
