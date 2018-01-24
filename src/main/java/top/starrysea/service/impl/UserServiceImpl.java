package top.starrysea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IAdminDao;
import top.starrysea.object.dto.Admin;
import top.starrysea.service.IUserService;

import static top.starrysea.common.ResultKey.*;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IAdminDao adminDao;

	@Override
	// 管理员登陆
	public ServiceResult loginService(Admin admin) {
		DaoResult daoResult = adminDao.loginDao(admin);
		ServiceResult sr = new ServiceResult();
		sr.setSuccessed(daoResult.isSuccessed());
		if (sr.isSuccessed()) {
			sr.setResult(ADMIN, daoResult.getResult(Admin.class));
		} else {
			sr.setErrInfo(daoResult.getErrInfo());
		}
		return sr;
	}

}
