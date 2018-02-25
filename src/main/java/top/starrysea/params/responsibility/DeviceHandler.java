package top.starrysea.params.responsibility;

import org.springframework.mobile.device.Device;

public class DeviceHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof Device) {
			Device device = (Device) object;
			String type = "UNKNOWN";
			if (device.isNormal()) {
				type = "电脑";
			} else if (device.isMobile()) {
				type = "手机";
			} else if (device.isTablet()) {
				type = "平板";
			}
			return device.getClass().getSimpleName() + "{\"deviceType\":" + type + "}";
		}
		return nextHandler.handleRequest(object);
	}

}
