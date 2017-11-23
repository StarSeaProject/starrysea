package top.starrysea.dao;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Work;

public interface IWorkDao {
	DaoResult getAllWorkDao(Condition condition, Work work);

	DaoResult getWorkCountDao(Condition condition, Work work);

	DaoResult getWorkDao(Work work);

	DaoResult saveWorkDao(Work work);

	DaoResult updateWorkStockDao(Work work);

	DaoResult deleteWorkDao(Work work);

	DaoResult getStockDao(Work work);
}
