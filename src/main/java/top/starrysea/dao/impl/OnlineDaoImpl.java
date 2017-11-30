package top.starrysea.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IOnlineDao;
import top.starrysea.object.dto.Online;

@Repository("onlineDao")
public class OnlineDaoImpl implements IOnlineDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate template;

	@Override
	public DaoResult saveOnlineDao(Online online) {
		String sql = "INSERT INTO online(online_id,online_email) " + "VALUES(?,?)";
		try {
			template.update(sql, online.getOnlineId(), online.getOnlineEmail());
		} catch (DuplicateKeyException e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false, "重复的email!");
		}
		return new DaoResult(true);
	}

	@Override
	public DaoResult getAllOnlineDao() {
		String sql = "SELECT online_email " + "FROM online";
		List<String> result = template.query(sql, new Object[] {}, (rs, row) -> rs.getString("online_email"));
		return new DaoResult(true, result);
	}

}
