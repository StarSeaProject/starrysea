package top.starrysea.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;

public interface IWorkImageDao {

	DaoResult getAllWorkImageDao(Work work);
	
	DaoResult saveWorkImageDao(List<WorkImage> workImages);
}
