package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Work;

public class WorkForAdd {
	@NotEmpty(message = "作品名称不能为空")
	@Length(max = 30, message = "作品名称长度不能超过30")
	private String workName;
	@NotNull(message = "作品库存不能为空")
	private Integer workStock;
	@NotEmpty(message = "作品概要不能为空")
	@Length(max = 50, message = "作品概要长度不能超过50")
	private String workSummary;

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

	public Work toDTO() {
		return new Work.Builder().workName(workName).workStock(workStock).workSummary(workSummary).build();
	}
}
