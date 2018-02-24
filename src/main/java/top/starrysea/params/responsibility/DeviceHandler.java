package top.starrysea.params.responsibility;

import org.springframework.mobile.device.Device;

import top.starrysea.params.strategy.DeviceToString;

public class DeviceHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof Device) {
			changeToString = new DeviceToString();
		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return changeToString.paramToString(object);
	}

}
