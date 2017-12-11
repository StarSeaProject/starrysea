package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IFundingDao;
import top.starrysea.object.dto.Funding;

@Repository("fundingDao")
public class FundingDaoImpl implements IFundingDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public DaoResult getAllFundingDao(Funding funding) {
		String sql = "SELECT funding_id,funding_name,funding_money,funding_message " + "FROM funding "
				+ "WHERE activity_id = ?";
		List<Funding> theResult = template.query(sql, new Object[] { funding.getActivity().getActivityId() },
				(rs, row) -> new Funding.Builder().fundingId(rs.getInt("funding_id"))
						.fundingName(rs.getString("funding_name")).fundingMoney(rs.getDouble("funding_money"))
						.fundingMessage(rs.getString("funding_message")).build());
		return new DaoResult(true, theResult);
	}

	@Override
	public DaoResult saveFundingDao(List<Funding> fundings) {
		String sql = "INSERT INTO funding(activity_id,funding_name,funding_money,funding_message) " + "VALUES(?,?,?,?)";
		template.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, fundings.get(i).getActivity().getActivityId());
				ps.setString(2, fundings.get(i).getFundingName());
				ps.setDouble(3, fundings.get(i).getFundingMoney());
				ps.setString(4, fundings.get(i).getFundingMessage());
			}

			@Override
			public int getBatchSize() {
				return fundings.size();
			}
		});
		return new DaoResult(true);
	}

	@Override
	public DaoResult deleteFundingDao(Funding funding) {
		String sql = "DELETE FROM funding " + "WHERE funding_id = ?";
		template.update(sql, funding.getFundingId());
		return new DaoResult(true);
	}

}
