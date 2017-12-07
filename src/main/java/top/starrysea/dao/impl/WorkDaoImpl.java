package top.starrysea.dao.impl;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.SqlWithParams;
import top.starrysea.dao.IWorkDao;
import top.starrysea.object.dto.Work;

import static top.starrysea.common.Common.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("workDao")
public class WorkDaoImpl implements IWorkDao {

	@Autowired
	private JdbcTemplate template;
	// 作品每页显示条数
	public final static int PAGE_LIMIT = 10;

	@Override
	// 查询所有作品
	public DaoResult getAllWorkDao(Condition condition, Work work) {
		SqlWithParams sqlWithParams = getTheSqlForGetAll(work);
		String sql = "SELECT work_id,work_name,work_cover,work_summary " + "FROM work " + sqlWithParams.getWhere()
				+ "ORDER BY work_uploadtime " + "LIMIT " + (condition.getPage() - 1) * PAGE_LIMIT + "," + PAGE_LIMIT;
		Object[] params = sqlWithParams.getParams();
		List<Work> theResult = template.query(sql, params,
				(rs, row) -> new Work.Builder().workId(rs.getInt("work_id")).workName(rs.getString("work_name"))
						.workCover(rs.getString("work_cover")).workSummary(rs.getString("work_summary")).build());
		return new DaoResult(true, theResult);
	}

	private SqlWithParams getTheSqlForGetAll(Work work) {
		StringBuilder whereBuffer = new StringBuilder();
		int insertIndex;
		Object[] preParams = new Object[1];
		int paramsIndex = 0;
		whereBuffer.append("WHERE 1=1 ");

		if (isNotNull(work.getWorkName())) {
			insertIndex = whereBuffer.indexOf("WHERE") + 5;
			whereBuffer.insert(insertIndex, " work_name LIKE ? AND ");
			preParams[paramsIndex] = "%" + work.getWorkName() + "%";
			paramsIndex++;
		}

		Object[] params = new Object[paramsIndex];
		System.arraycopy(preParams, 0, params, 0, paramsIndex);
		return new SqlWithParams(whereBuffer.toString(), params);
	}

	@Override
	// 查询所有作品的数量，用于分页
	public DaoResult getWorkCountDao(Condition condition, Work work) {
		SqlWithParams sqlWithParams = getTheSqlForGetAll(work);
		String sql = "SELECT COUNT(*) " + "FROM work " + sqlWithParams.getWhere();
		Object[] params = sqlWithParams.getParams();
		Integer theResult = template.queryForObject(sql, params, Integer.class);
		return new DaoResult(true, theResult);
	}

	@Override
	// 查询一个作品的详情页
	public DaoResult getWorkDao(Work work) {
		String sql = "UPDATE work " + "SET work_click = work_click + 1 " + "WHERE work_id = ?";
		template.update(sql, work.getWorkId());
		sql = "SELECT work_name,work_uploadtime,work_pdfpath,work_click " + "FROM work " + "WHERE work_id = ?";
		Work theResult = template.queryForObject(sql, new Object[] { work.getWorkId() },
				(rs, row) -> new Work.Builder().workName(rs.getString("work_name"))
						.workUploadTime(date2String(rs.getDate("work_uploadtime")))
						.workPdfpath(rs.getString("work_pdfpath")).workClick(rs.getInt("work_click")).build());
		return new DaoResult(true, theResult);
	}

	@Override
	// 添加一个作品
	public DaoResult saveWorkDao(Work work) {
		String sql = "INSERT INTO work(work_name,work_uploadtime,work_pdfpath,work_stock,work_cover,work_summary) "
				+ "VALUES(?,?,?,?,?,?)";
		template.update(sql, work.getWorkName(), work.getWorkUploadTime(), work.getWorkPdfpath(), work.getWorkStock(),
				work.getWorkCover(), work.getWorkSummary());
		return new DaoResult(true);
	}

	@Override
	// 管理员删除一个作品
	public DaoResult deleteWorkDao(Work work) {
		String sql = "DELETE FROM work " + "WHERE work_id = ?";
		template.update(sql, work.getWorkId());
		return new DaoResult(true);
	}

	@Override
	// 减少一个作品的库存
	public DaoResult updateWorkStockDao(Work work) {
		String sql = "UPDATE work " + "SET work_stock = work_stock - ? " + "WHERE work_id = ?";
		template.update(sql, work.getWorkStock(), work.getWorkId());
		return new DaoResult(true);
	}

	@Override
	public DaoResult getStockDao(Work work) {
		String sql = "SELECT work_stock " + "FROM work " + "WHERE work_id = ?";
		Integer theResult = template.queryForObject(sql, new Object[] { work.getWorkId() }, Integer.class);
		return new DaoResult(true, theResult);
	}

}
