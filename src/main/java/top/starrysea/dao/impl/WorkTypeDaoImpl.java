package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IWorkTypeDao;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

@Repository("workTypeDao")
public class WorkTypeDaoImpl implements IWorkTypeDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult getAllWorkTypeDao(WorkType workType) {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("work_type_id").select("name").select("stock").from(WorkType.class)
				.where("work_id", WhereType.EQUALS, workType.getWork().getWorkId())
				.endForList((rs, row) -> new WorkType.Builder().workTypeId(rs.getInt("work_type_id"))
						.name(rs.getString("name")).stock(rs.getInt("stock")).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult getWorkTypeStockDao(WorkType workType) {
		kumaSqlDao.selectMode();
		EntitySqlResult<WorkType> theResult = kumaSqlDao.select("stock").from(WorkType.class)
				.leftjoin(Work.class, "w", "work_id", WorkType.class, "work_id")
				.where("work_type_id", WhereType.EQUALS, workType.getWorkTypeId())
				.where("work_id", "w", WhereType.EQUALS, workType.getWork().getWorkId())
				.endForObject((rs, row) -> new WorkType.Builder().stock(rs.getInt("stock")).build());
		return new DaoResult(true, theResult.getResult().getStock());
	}

	@Override
	public DaoResult getWorkTypeNameDao(WorkType workType) {
		kumaSqlDao.selectMode();
		EntitySqlResult<WorkType> theResult = kumaSqlDao.select("name").from(WorkType.class)
				.where("work_type_id", WhereType.EQUALS, workType.getWorkTypeId())
				.endForObject((rs, row) -> new WorkType.Builder().name(rs.getString("name")).build());
		WorkType wt = theResult.getResult();
		wt.setWorkTypeId(workType.getWorkTypeId());
		return new DaoResult(true, wt);
	}

	@Override
	public DaoResult saveWorkTypeDao(List<WorkType> workTypes) {
		kumaSqlDao.insertMode();
		kumaSqlDao.insert("work_id").insert("name").insert("stock").table(WorkType.class)
				.batchEnd(new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, workTypes.get(i).getWork().getWorkId());
						ps.setString(2, workTypes.get(i).getName());
						ps.setInt(3, workTypes.get(i).getStock());
					}

					@Override
					public int getBatchSize() {
						return workTypes.size();
					}
				});
		return new DaoResult(true);
	}

	@Override
	public DaoResult deleteWorkTypeDao(WorkType workType) {
		kumaSqlDao.deleteMode();
		kumaSqlDao.table(WorkType.class).where("work_type_id", WhereType.EQUALS, workType.getWorkTypeId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult updateWorkTypeStockDao(WorkType workType) {
		kumaSqlDao.updateMode();
		kumaSqlDao.update("stock", UpdateSetType.ASSIGN, workType.getStock()).table(WorkType.class)
				.where("work_type_id", WhereType.EQUALS, workType.getWorkTypeId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult reduceWorkTypeStockDao(WorkType workType) {
		kumaSqlDao.updateMode();
		kumaSqlDao.update("stock", UpdateSetType.REDUCE, workType.getStock()).table(WorkType.class)
				.where("work_type_id", WhereType.EQUALS, workType.getWorkTypeId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult updateWorkTypeStockDao(Orders order) {
		kumaSqlDao.selectMode();
		EntitySqlResult<WorkType> theResult = kumaSqlDao.select("work_type_id").from(Orders.class)
				.where("order_id", WhereType.EQUALS, order.getOrderId())
				.endForObject((rs, row) -> new WorkType.Builder().workTypeId(rs.getInt("work_type_id")).build());
		kumaSqlDao.updateMode();
		kumaSqlDao.update("stock", UpdateSetType.ADD, 1).table(WorkType.class)
				.where("work_type_id", WhereType.EQUALS, theResult.getResult().getWorkTypeId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult getAllWorkTypeForShoppingCarDao(List<WorkType> workTypes) {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("work_type_id").select("name").select("work_id", "w")
				.select("work_name", "w").from(WorkType.class, "wt")
				.innerjoin(Work.class, "w", "work_id", WorkType.class,
						"work_id")
				.where("work_type_id", WhereType.IN,
						workTypes.stream().map(WorkType::getWorkTypeId).collect(Collectors.toList()))
				.endForList((rs, row) -> new WorkType.Builder().workTypeId(rs.getInt("work_type_id"))
						.name(rs.getString("name")).work(new Work.Builder().workId(rs.getInt("w.work_id"))
								.workName(rs.getString("w.work_name")).build())
						.build());
		return new DaoResult(true, theResult.getResult());
	}

}
