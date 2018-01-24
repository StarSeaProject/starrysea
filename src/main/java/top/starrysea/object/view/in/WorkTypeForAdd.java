package top.starrysea.object.view.in;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.WorkType;

public class WorkTypeForAdd {

	@NotEmpty(message = "作品类型名不能为空")
	private String name;
	@NotNull(message = "作品类型库存不能为空")
	private Integer stock;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public WorkType toDTO() {
		return new WorkType.Builder().name(name).stock(stock).build();
	}
}
