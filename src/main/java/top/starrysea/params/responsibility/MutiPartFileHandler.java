package top.starrysea.params.responsibility;

import org.springframework.web.multipart.MultipartFile;

public class MutiPartFileHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof MultipartFile) {
			MultipartFile file = (MultipartFile) object;
			return file.getClass().getSimpleName() + "{\"originalFilename\":" + file.getOriginalFilename()
					+ ",\"contentType\":" + file.getContentType() + ",\"size\":" + file.getSize() + "}";
		}
		return nextHandler.handleRequest(object);
	}

}
