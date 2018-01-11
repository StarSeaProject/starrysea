package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IWorkImageDao;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.WorkImage;

@Repository("workImageDao")
public class WorkImageDaoImpl implements IWorkImageDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult getAllWorkImageDao(WorkImage workImage) {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("work_image_path").from(WorkImage.class)
				.where("work_id", WhereType.EQUALS, workImage.getWork().getWorkId()).endForList(
						(rs, row) -> new WorkImage.Builder().workImagePath(rs.getString("work_image_path")).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult saveWorkImageDao(List<WorkImage> workImages) {
		kumaSqlDao.insertMode();
		kumaSqlDao.insert("work_id").insert("work_image_path").table(WorkImage.class)
				.batchEnd(new BatchPreparedStatementSetter() {

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
