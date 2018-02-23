package top.starrysea.params.responsibility;

import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;

import top.starrysea.common.Condition;
import top.starrysea.params.strategy.BResultToString;
import top.starrysea.params.strategy.ConditionToString;
import top.starrysea.params.strategy.DeviceToString;
import top.starrysea.params.strategy.SessionToString;

public class CommonHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof Device) {
			changeToString = new DeviceToString();
		} else if (object instanceof BindingResult) {
			changeToString = new BResultToString();
		} else if (object instanceof HttpSession) {
			changeToString = new SessionToString();
		} else if (object instanceof Condition) {
			changeToString = new ConditionToString();
		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return changeToString.paramToString(object);
	}

}
