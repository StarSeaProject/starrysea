package top.starrysea.object.view.in;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

public class WorkForAdd {
	@NotEmpty(message = "作品名称不能为空")
	@Length(max = 30, message = "作品名称长度不能超过30")
	private String workName;
	@NotEmpty(message = "作品概要不能为空")
	@Length(max = 50, message = "作品概要长度不能超过50")
	private String workSummary;
	@NotEmpty(message = "作品pdf文件路径不能为空")
	@Length(max = 50, message = "作品pdf文件路径长度不能超过50")
	@URL(message = "作品pdf文件路径不是一个合法的网址")
	private String workPdfpath;
	@Valid
	@NotEmpty(message="没有作品类型")
	private List<WorkTypeForAdd> workTypes;

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkSummary() {
		return workSummary;
	}

	public void setWorkSummary(String workSummary) {
		this.workSummary = workSummary;
	}

	public String getWorkPdfpath() {
		return workPdfpath;
	}

	public void setWorkPdfpath(String workPdfpath) {
		this.workPdfpath = workPdfpath;
	}

	public List<WorkTypeForAdd> getWorkTypes() {
		return workTypes;
	}

	public void setWorkTypes(List<WorkTypeForAdd> workTypes) {
		this.workTypes = workTypes;
	}

	public Work toDTO() {
		return new Work.Builder().workName(workName).workSummary(workSummary)
				.workPdfpath(workPdfpath).build();
	}
	
	public List<WorkType> toDTOWorkType(){
		return workTypes.stream().map(WorkTypeForAdd::toDTO).collect(Collectors.toList());
	}
}
