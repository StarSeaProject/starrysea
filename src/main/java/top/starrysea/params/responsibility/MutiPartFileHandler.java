package top.starrysea.params.responsibility;

import org.springframework.web.multipart.MultipartFile;

import top.starrysea.params.strategy.MutipartFileToString;

public class MutiPartFileHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof MultipartFile) {
			changeToString = new MutipartFileToString();
		} else {
			return nextHandler.handleRequest(object);
		}
		return changeToString.paramToString(object);
	}

}
