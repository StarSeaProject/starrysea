package top.starrysea.service;

import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Work;

public interface IWorkService {
	ServiceResult queryAllWorkService(Condition condition, Work work);

	ServiceResult queryWorkService(Work work);

	ServiceResult addWorkService(MultipartFile file, Work work);

	ServiceResult removeWorkService(Work work);
}
