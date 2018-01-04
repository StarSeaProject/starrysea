package top.starrysea.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IAdminDao;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.kql.facede.OperationType;
import top.starrysea.object.dto.Admin;

import static top.starrysea.common.Common.isNotNull;
import static top.starrysea.common.Common.md5;

@Repository("adminDao")
public class AdminDaoImpl implements IAdminDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	// 管理员登陆
	public DaoResult loginDao(Admin admin) {
		kumaSqlDao.changeMode(OperationType.SELECT);
		ListSqlResult list = kumaSqlDao.select("1").from(Admin.class)
				.where("admin_useraccount", WhereType.EQUALS, admin.getAdminUseraccount()).endForList(String.class);
		if (list.getResult().isEmpty()) {
			return new DaoResult(false, "管理员账号不存在");
		}
		kumaSqlDao.changeMode(OperationType.SELECT);
		ListSqlResult theResult = kumaSqlDao.select("admin_id").from(Admin.class)
				.where("admin_useraccount", WhereType.EQUALS, admin.getAdminUseraccount())
				.where("admin_password", WhereType.EQUALS, md5(admin.getAdminPassword()))
				.endForList((rs, row) -> new Admin.Builder().adminId(rs.getInt("admin_id")).build());
		if (isNotNull(theResult.getResult())) {
			Admin result = (Admin) theResult.getResult().get(0);
			return new DaoResult(true, result);
		} else {
			return new DaoResult(false, "密码错误");
		}
	}

}
