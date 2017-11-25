package top.starrysea.object.view.in;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Work;

public class WorkForAdd {
	@NotEmpty(message = "作品名称不能为空")
	@Length(max = 30, message = "作品名称长度不能超过30")
	private String workName;
	@NotEmpty(message = "作品上传时间不能为空")
	private String workUploadTime;
	@NotEmpty(message = "作品路径不能为空")
	@Length(max = 50, message = "作品路径长度不能超过50")
	private String workPdfpath;
	@NotEmpty(message = "作品库存不能为空")
	private Integer workStock;

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkUploadTime() {
		return workUploadTime;
	}

	public void setWorkUploadTime(String workUploadTime) {
		this.workUploadTime = workUploadTime;
	}

	public String getWorkPdfpath() {
		return workPdfpath;
	}

	public void setWorkPdfpath(String workPdfpath) {
		this.workPdfpath = workPdfpath;
	}

	public Integer getWorkStock() {
		return workStock;
	}

	public void setWorkStock(Integer workStock) {
		this.workStock = workStock;
	}

	public Work toDTO() {
		return new Work.Builder().workName(workName).workPdfpath(workPdfpath).workStock(workStock)
				.workUploadTime(workUploadTime).build();
	}
}
