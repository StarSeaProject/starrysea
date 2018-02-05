package top.starrysea.strategy;

import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.strategy.ChangeToString;

/*
 * 具体策略算法
 */
public class WAllToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		WorkForAll workForAll = (WorkForAll) object;
		return "WorkForAll{\"workName\":" + workForAll.getWorkName() + ",Condition{\"page\":"
				+ workForAll.getCondition().getPage() + "}}";
	}

}
