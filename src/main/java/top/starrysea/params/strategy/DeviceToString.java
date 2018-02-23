package top.starrysea.params.strategy;

import org.springframework.mobile.device.Device;

public class DeviceToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
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

}
