package top.starrysea.dao.impl;

import static top.starrysea.common.Common.isNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.SqlWithParams;
import top.starrysea.dao.IActivityDao;
import top.starrysea.object.dto.Activity;

@Repository("activityDao")
public class ActivityDaoImpl implements IActivityDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate template;
	// 众筹每页显示条数
	public static final int PAGE_LIMIT = 10;

	@Override
	// 查询所有众筹活动
	public DaoResult getAllActivityDao(Condition condition, Activity activity) {
		SqlWithParams sqlWithParams = getTheSqlForGetAll(activity);
		String sql = "SELECT activity_id,activity_name,activity_cover,activity_summary,activity_endtime "
				+ "FROM activity " + sqlWithParams.getWhere() + "ORDER BY activity_id DESC " + "LIMIT "
				+ (condition.getPage() - 1) * PAGE_LIMIT + "," + PAGE_LIMIT;
		Object[] params = sqlWithParams.getParams();
		try {
			List<Activity> theResult = template.query(sql, params,
					(rs, row) -> new Activity.Builder().activityId(rs.getInt("activity_id"))
							.activityName(rs.getString("activity_name")).activityCover(rs.getString("activity_cover"))
							.activitySummary(rs.getString("activity_summary"))
							.activityEndtime(Common.date2String(rs.getDate("activity_endtime"))).build());
			return new DaoResult(true, theResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false, "查询失败,原因是:" + e.getMessage());
		}
	}

	@Override
	// 查询所有众筹活动的数量，用于分页
	public DaoResult getActivityCountDao(Condition condition, Activity activity) {
		try {
			SqlWithParams sqlWithParams = getTheSqlForGetAll(activity);
			String sql = "SELECT COUNT(*) " + "FROM activity " + sqlWithParams.getWhere();
			Object[] params = sqlWithParams.getParams();
			Integer theResult = template.queryForObject(sql, params, Integer.class);
			return new DaoResult(true, theResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false, "查询失败,原因是:" + e.getMessage());
		}
	}

	@Override
	// 查询一个众筹活动的详情页
	public DaoResult getActivityDao(Activity activity) {
		String sql = "SELECT activity_name,activity_content,activity_status " + "FROM activity "
				+ "WHERE activity_id = ?";
		try {
			Activity theResult = template.queryForObject(sql, new Object[] { activity.getActivityId() },
					(rs, row) -> new Activity.Builder().activityName(rs.getString("activity_name"))
							.activityContent(rs.getString("activity_content"))
							.activityStatus(rs.getShort("activity_status")).build());
			return new DaoResult(true, theResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false, "查询失败,原因是:" + e.getMessage());
		}
	}

	@Override
	// 添加一个众筹活动
	public DaoResult saveActivityDao(Activity activity) {
		String sql = "INSERT INTO activity(activity_name,activity_content,activity_status,activity_cover,activity_summary) "
				+ "VALUES(?,?,?,?,?)";
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			template.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, activity.getActivityName());
					ps.setString(2, activity.getActivityContent());
					ps.setShort(3, activity.getActivityStatus());
					ps.setString(4, activity.getActivityCover());
					ps.setString(5, activity.getActivitySummary());
					return ps;
				}
			}, keyHolder);
			return new DaoResult(true, keyHolder.getKey().intValue());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false, "添加失败,原因是:" + e.getMessage());
		}

	}

	@Override
	// 修改一个众筹活动的状态
	public DaoResult updateActivityDao(Activity activity) {
		try {
			String sql = "";
			// 如果该活动已经结束,则记录结束的时间
			if (activity.getActivityStatus() == 3) {
				sql = "UPDATE activity " + "SET activity_status = ? , activity_endtime = ?" + "WHERE activity_id = ?";
				template.update(sql, activity.getActivityStatus(), Common.getNowDate(), activity.getActivityId());
			} else {
				sql = "UPDATE activity " + "SET activity_status = ? " + "WHERE activity_id = ?";
				template.update(sql, activity.getActivityStatus(), activity.getActivityId());
			}
			return new DaoResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false, "修改失败,原因是" + e.getMessage());
		}
	}

	@Override
	// 删除一个众筹活动
	public DaoResult deleteActivityDao(Activity activity) {
		String sql = "DELETE FROM activity " + "WHERE activity_id = ?";
		try {
			template.update(sql, activity.getActivityId());
			return new DaoResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false, "删除失败，原因是：" + e.getMessage());
		}
	}

	private SqlWithParams getTheSqlForGetAll(Activity activity) {
		StringBuilder whereBuffer = new StringBuilder();
		int insertIndex;
		Object[] preParams = new Object[1];
		int paramsIndex = 0;
		whereBuffer.append("WHERE 1=1 ");

		if (isNotNull(activity.getActivityName())) {
			insertIndex = whereBuffer.indexOf("WHERE") + 5;
			whereBuffer.insert(insertIndex, " activity_name LIKE ? AND ");
			preParams[paramsIndex] = "%" + activity.getActivityName() + "%";
			paramsIndex++;
		}
		Object[] params = new Object[paramsIndex];
		System.arraycopy(preParams, 0, params, 0, paramsIndex);
		return new SqlWithParams(whereBuffer.toString(), params);
	}

}
