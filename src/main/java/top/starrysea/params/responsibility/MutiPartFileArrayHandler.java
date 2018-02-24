package top.starrysea.params.responsibility;

import org.springframework.web.multipart.MultipartFile;

import top.starrysea.params.strategy.MutipartFileArrayToString;

public class MutiPartFileArrayHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof MultipartFile[]) {
			changeToString = new MutipartFileArrayToString();
		} else {
			if (nextHandler != null) {
				return nextHandler.handleRequest(object);
			}
		}
		return changeToString.paramToString(object);
	}

}
