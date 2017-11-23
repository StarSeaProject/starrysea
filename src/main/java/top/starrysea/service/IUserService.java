package top.starrysea.service;

import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Admin;

public interface IUserService {
	ServiceResult loginService(Admin admin);
}
