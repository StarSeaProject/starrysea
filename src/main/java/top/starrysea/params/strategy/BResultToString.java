package top.starrysea.params.strategy;

import org.springframework.validation.BindingResult;

public class BResultToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		BindingResult bindingResult = (BindingResult) object;
		return bindingResult.getClass().getSimpleName() + "{\"hasError:\":" + bindingResult.hasErrors()
				+ ",\"errorsCount\":" + bindingResult.getErrorCount() + "}";
	}

}