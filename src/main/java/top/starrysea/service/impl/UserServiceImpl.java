package top.starrysea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IAdminDao;
import top.starrysea.object.dto.Admin;
import top.starrysea.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IAdminDao adminDao;
	
	@Override
	// 管理员登陆
	public ServiceResult loginService(Admin admin) {
		return new ServiceResult(adminDao.loginDao(admin));
	}

}
