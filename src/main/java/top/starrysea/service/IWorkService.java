package top.starrysea.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;

public interface IWorkService {
	ServiceResult queryAllWorkService(Condition condition, Work work);

	ServiceResult queryWorkService(Work work);

	ServiceResult addWorkService(MultipartFile file, Work work, List<WorkImage> workImages);

	ServiceResult removeWorkService(Work work);
}
