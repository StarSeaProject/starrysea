package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IActivityImageDao;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.ActivityImage;

@Repository("activityImageDao")
public class ActivityImageDaoImpl implements IActivityImageDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult getAllActivityImageDao(Activity activity) {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("activity_image_path").from(ActivityImage.class)
				.where("activity_id", WhereType.EQUALS, activity.getActivityId())
				.endForList((rs, row) -> new ActivityImage.Builder()
						.activityImagePath(rs.getString("activity_image_path")).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult saveActivityImageDao(List<ActivityImage> activityImages) {
		kumaSqlDao.insertMode();
		kumaSqlDao.insert("activity_id").insert("activity_image_path").table(ActivityImage.class)
				.batchEnd(new BatchPreparedStatementSetter() {

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
