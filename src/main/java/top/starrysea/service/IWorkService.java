package top.starrysea.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.entity.Work;

public interface IWorkService {
	ServiceResult queryAllWorkService(Condition condition, Work work);

	ServiceResult queryWorkService(Work work);

	ServiceResult addWorkService(CommonsMultipartFile file, Work work);

	ServiceResult removeWorkService(Work work);
}
