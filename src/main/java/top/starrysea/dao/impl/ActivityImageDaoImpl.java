package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IActivityImageDao;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;

@Repository("activityImageDao")
public class ActivityImageDaoImpl implements IActivityImageDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public DaoResult getAllActivityImageDao(Activity activity) {
		String sql = "SELECT activity_image_path " + "FROM activity_image " + "WHERE activity_id = ?";
		List<ActivityImage> theResult = template.query(sql, new Object[] { activity.getActivityId() },
				(rs, row) -> new ActivityImage.Builder().activityImagePath(rs.getString("work_image_path")).build());
		return new DaoResult(true, theResult);
	}

	@Override
	public DaoResult saveActivityImageDao(List<ActivityImage> activityImages) {
		String sql = "INSERT INTO activity_image(activity_id,activity_image_path) " + "VALUES(?,?)";
		template.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, activityImages.get(i).getActivity().getActivityId());
				ps.setString(2, activityImages.get(i).getActivityImagePath());
			}

			@Override
			public int getBatchSize() {
				return activityImages.size();
			}
		});
		return new DaoResult(true);
	}

}
