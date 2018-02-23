package top.starrysea.params.strategy;

import org.springframework.validation.BindingResult;
import static top.starrysea.common.Common.getFieldErrors;

public class BResultToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		BindingResult bindingResult = (BindingResult) object;
		return bindingResult.getClass().getSimpleName() + getFieldErrors(bindingResult);
	}

}
