package top.starrysea.params.responsibility;

import org.springframework.validation.BindingResult;

import top.starrysea.params.strategy.BResultToString;

public class BResultHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof BindingResult) {
			changeToString = new BResultToString();
		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return changeToString.paramToString(object);
	}

}
