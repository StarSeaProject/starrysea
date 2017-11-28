package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IWorkImageDao;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;

@Repository("workImageDao")
public class WorkImageDaoImpl implements IWorkImageDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public DaoResult getAllWorkImageDao(Work work) {
		String sql = "SELECT work_image_path " + "FROM work_image " + "WHERE work_id = ?";
		List<WorkImage> theResult = template.query(sql, new Object[] { work.getWorkId() },
				(rs, row) -> new WorkImage.Builder().workImagePath(rs.getString("work_image_path")).build());
		return new DaoResult(true, theResult);
	}

	@Override
	public DaoResult saveWorkImageDao(List<WorkImage> workImages) {
		String sql="INSERT INTO work_image(work_id,work_image_path) "
				+ "VALUES(?,?)";
		template.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, workImages.get(i).getWork().getWorkId());
				ps.setString(2, workImages.get(i).getWorkImagePath());
			}
			
			@Override
			public int getBatchSize() {
				return workImages.size();
			}
		});
		return new DaoResult(true);
	}

}
