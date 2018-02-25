package top.starrysea.params.responsibility;

import org.springframework.validation.BindingResult;

public class BResultHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof BindingResult) {
			BindingResult bindingResult = (BindingResult) object;
			return bindingResult.getClass().getSimpleName() + "{\"hasError:\":" + bindingResult.hasErrors()
					+ ",\"errorsCount\":" + bindingResult.getErrorCount() + "}";
		}
		return nextHandler.handleRequest(object);
	}

}
