package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IActivityDao;
import top.starrysea.kql.clause.OrderByType;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.IntegerSqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.kql.facede.UpdateSqlResult;
import top.starrysea.object.dto.Activity;

@Repository("activityDao")
public class ActivityDaoImpl implements IActivityDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;
	// 众筹每页显示条数
	public static final int PAGE_LIMIT = 10;

	@Override
	public DaoResult getNewestActivityDao() {
		kumaSqlDao.selectMode();
		EntitySqlResult theResult = kumaSqlDao.select("activity_id").select("activity_name").select("activity_cover")
				.select("activity_summary").select("activity_endtime").from(Activity.class)
				.orderBy("activity_id", OrderByType.DESC).limit(1)
				.endForObject((rs, row) -> new Activity.Builder().activityId(rs.getInt("activity_id"))
						.activityName(rs.getString("activity_name")).activityCover(rs.getString("activity_cover"))
						.activitySummary(rs.getString("activity_summary"))
						.activityEndtime(Common.date2String(rs.getDate("activity_endtime"))).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	// 查询所有众筹活动
	public DaoResult getAllActivityDao(Condition condition, Activity activity) {
		int start = 0;
		if (condition.getPage() == 1) {
			start = 1;
		} else {
			start = (condition.getPage() - 1) * PAGE_LIMIT;
		}
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("activity_id").select("activity_name").select("activity_cover")
				.select("activity_summary").select("activity_endtime").from(Activity.class)
				.where("activity_name", WhereType.FUZZY, activity.getActivityName())
				.orderBy("activity_id", OrderByType.DESC).limit(start, PAGE_LIMIT)
				.endForList((rs, row) -> new Activity.Builder().activityId(rs.getInt("activity_id"))
						.activityName(rs.getString("activity_name")).activityCover(rs.getString("activity_cover"))
						.activitySummary(rs.getString("activity_summary"))
						.activityEndtime(Common.date2String(rs.getDate("activity_endtime"))).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	// 查询所有众筹活动的数量，用于分页
	public DaoResult getActivityCountDao(Condition condition, Activity activity) {
		kumaSqlDao.selectMode();
		IntegerSqlResult theResult = kumaSqlDao.select(SelectClause.COUNT).from(Activity.class)
				.where("activity_name", WhereType.FUZZY, activity.getActivityName()).endForNumber();
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	// 查询一个众筹活动的详情页
	public DaoResult getActivityDao(Activity activity) {
		kumaSqlDao.selectMode();
		EntitySqlResult theResult = kumaSqlDao.select("activity_name").select("activity_content")
				.select("activity_status").select("activity_money").from(Activity.class)
				.where("activity_id", WhereType.EQUALS, activity.getActivityId())
				.endForObject((rs, row) -> new Activity.Builder().activityName(rs.getString("activity_name"))
						.activityContent(rs.getString("activity_content"))
						.activityStatus(rs.getShort("activity_status")).activityMoney(rs.getDouble("activity_money"))
						.build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	// 添加一个众筹活动
	public DaoResult saveActivityDao(Activity activity) {
		kumaSqlDao.insertMode();
		UpdateSqlResult theResult = kumaSqlDao.insert("activity_name", activity.getActivityName())
				.insert("activity_content", activity.getActivityContent())
				.insert("activity_status", activity.getActivityStatus())
				.insert("activity_cover", activity.getActivityCover())
				.insert("activity_summary", activity.getActivitySummary()).table(Activity.class).end();
		return new DaoResult(true, theResult.getKeyHolder().getKey().intValue());
	}

	@Override
	// 修改一个众筹活动的状态
	public DaoResult updateActivityDao(Activity activity) {
		kumaSqlDao.updateMode();
		if (activity.getActivityStatus() == 3) {
			kumaSqlDao.update("activity_status", UpdateSetType.ASSIGN, activity.getActivityStatus())
					.update("activity_endtime", UpdateSetType.ASSIGN, activity.getActivityEndtime())
					.where("activity_id", WhereType.EQUALS, activity.getActivityId()).table(Activity.class).end();
		} else {
			kumaSqlDao.update("activity_status", UpdateSetType.ASSIGN, activity.getActivityStatus())
					.where("activity_id", WhereType.EQUALS, activity.getActivityId()).table(Activity.class).end();
		}
		return new DaoResult(true);
	}

	@Override
	// 删除一个众筹活动
	public DaoResult deleteActivityDao(Activity activity) {
		kumaSqlDao.deleteMode();
		kumaSqlDao.table(Activity.class).where("activity_id", WhereType.EQUALS, activity.getActivityId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult updateAddActivityMoneyDao(List<Activity> activitys) {
		kumaSqlDao.updateMode();
		kumaSqlDao.update("activity_money", UpdateSetType.ADD, null).where("activity_id", WhereType.EQUALS, null)
				.table(Activity.class).batchEnd(new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setDouble(1, activitys.get(i).getActivityMoney());
						ps.setInt(2, activitys.get(i).getActivityId());
					}

					@Override
					public int getBatchSize() {
						return activitys.size();
					}
				});
		return new DaoResult(true);
	}

	@Override
	public DaoResult updateReduceActivityMoneyDao(Activity activity) {
		kumaSqlDao.updateMode();
		kumaSqlDao.update("activity_money", UpdateSetType.REDUCE, activity.getActivityMoney())
				.where("activity_id", WhereType.EQUALS, activity.getActivityId()).table(Activity.class).end();
		return new DaoResult(true);
	}

}
