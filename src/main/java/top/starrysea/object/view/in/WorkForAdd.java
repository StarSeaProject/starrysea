package top.starrysea.object.view.in;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import top.starrysea.object.dto.ActivityImage;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;

public class WorkForAdd {
	@NotEmpty(message = "作品名称不能为空")
	@Length(max = 30, message = "作品名称长度不能超过30")
	private String workName;
	@NotNull(message = "作品库存不能为空")
	private Integer workStock;
	@NotEmpty(message = "作品概要不能为空")
	@Length(max = 50, message = "作品概要长度不能超过50")
	private String workSummary;
	@NotEmpty(message = "作品pdf文件路径不能为空")
	@Length(max = 50, message = "作品pdf文件路径长度不能超过50")
	@URL(message = "作品pdf文件路径不是一个合法的网址")
	private String workPdfpath;

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Integer getWorkStock() {
		return workStock;
	}

	public void setWorkStock(Integer workStock) {
		this.workStock = workStock;
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

	public Work toDTO() {
		return new Work.Builder().workName(workName).workStock(workStock).workSummary(workSummary)
				.workPdfpath(workPdfpath).build();
	}
}
