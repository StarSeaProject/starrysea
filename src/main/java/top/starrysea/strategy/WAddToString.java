package top.starrysea.strategy;

import top.starrysea.object.view.in.WorkForAdd;

/*
 * 具体策略算法
 */
public class WAddToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		WorkForAdd workForAdd = (WorkForAdd) object;
		return "workForAdd{\"workName\":" + workForAdd.getWorkName() + ",\"workPdfPath\":" + workForAdd.getWorkPdfpath()
				+ "}";
	}

}
