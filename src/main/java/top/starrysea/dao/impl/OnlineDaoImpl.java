package top.starrysea.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IOnlineDao;
import top.starrysea.object.dto.Online;

@Repository("onlineDao")
public class OnlineDaoImpl implements IOnlineDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public DaoResult saveOnlineDao(Online online) {
		String sql = "INSERT INTO online(online_id,online_email) " + "VALUES(?,?)";
		template.update(sql, online.getOnlineId(), online.getOnlineEmail());
		return new DaoResult(true);
	}

	@Override
	public DaoResult getAllOnlineDao() {
		String sql = "SELECT online_email " + "FROM online";
		List<String> result = template.query(sql, new Object[] {}, (rs, row) -> rs.getString("online_email"));
		return new DaoResult(true, result);
	}

}
