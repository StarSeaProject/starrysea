package top.starrysea.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

public interface IWorkService {
	ServiceResult queryAllWorkService(Condition condition, Work work);

	ServiceResult queryWorkService(Work work);

	ServiceResult addWorkService(MultipartFile coverFile, MultipartFile[] imageFiles, Work work,
			List<WorkType> workTypes);

	ServiceResult removeWorkService(Work work);
	
	ServiceResult removeWorkTypeService(WorkType workType);
	
	ServiceResult modifyWorkTypeService(WorkType workType);
}
