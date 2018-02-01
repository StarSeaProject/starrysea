package top.starrysea.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.WorkType;

public interface IWorkTypeDao {

	DaoResult getAllWorkTypeDao(WorkType workType);
	
	DaoResult getWorkTypeStockDao(WorkType workType);
	
	DaoResult getWorkTypeNameDao(WorkType workType);
	
	DaoResult saveWorkTypeDao(List<WorkType> workTypes);
	
	DaoResult deleteWorkTypeDao(WorkType workType);
	
	DaoResult updateWorkTypeStockDao(WorkType workType);
	
	DaoResult reduceWorkTypeStockDao(WorkType workType);
	
	DaoResult updateWorkTypeStockDao(Orders order);
	
	DaoResult getAllWorkTypeForShoppingCarDao(List<WorkType> workTypes);
}
