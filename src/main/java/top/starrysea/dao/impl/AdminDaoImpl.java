package top.starrysea.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IAdminDao;
import top.starrysea.object.dto.Admin;

import static top.starrysea.common.Common.isNotNull;
import static top.starrysea.common.Common.md5;

@Repository("adminDao")
public class AdminDaoImpl implements IAdminDao {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	// 管理员登陆
	public DaoResult loginDao(Admin admin) {
		String sql = "SELECT 1 " + "FROM admin " + "WHERE admin_useraccount = ?";
		List<String> list = template.queryForList(sql, new Object[] { admin.getAdminUseraccount() }, String.class);
		if (list.size() == 0) {
			return new DaoResult(false, "管理员账号不存在");
		}
		sql = "SELECT admin_id " + "FROM admin " + "WHERE admin_useraccount = ? " + "AND admin_password = ?";
		List<Admin> theResult = template.query(sql,
				new Object[] { admin.getAdminUseraccount(), md5(admin.getAdminPassword()) },
				(rs, row) -> new Admin.Builder().adminId(rs.getInt("admin_id")).build());
		if (isNotNull(theResult)) {
			return new DaoResult(true, theResult.get(0));
		} else {
			return new DaoResult(false, "密码错误");
		}
	}

}
