package top.starrysea.strategy;

import java.util.Map;

/*
 * 具体策略算法
 */
public class MapToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		Map<String, Object> map = (Map<String, Object>) object;
		return null;
	}

}
