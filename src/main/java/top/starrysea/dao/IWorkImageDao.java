package top.starrysea.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.WorkImage;

public interface IWorkImageDao {

	DaoResult getAllWorkImageDao(WorkImage workImage);

	DaoResult saveWorkImageDao(List<WorkImage> workImages);
}
