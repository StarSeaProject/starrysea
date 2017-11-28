package top.starrysea.object.view.in;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkImage;

public class WorkForAdd {
	@NotEmpty(message = "作品名称不能为空")
	@Length(max = 30, message = "作品名称长度不能超过30")
	private String workName;
	@NotEmpty(message = "作品库存不能为空")
	private Integer workStock;
	private List<WorkImageForAdd> workImages;

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

	public Work toDTO_work() {
		return new Work.Builder().workName(workName).workStock(workStock).build();
	}

	public List<WorkImage> toDTO_workImage() {
		return workImages.stream()
				.map((workImage) -> new WorkImage.Builder().workImagePath(workImage.getWorkImagePath()).build())
				.collect(Collectors.toList());
	}
}
