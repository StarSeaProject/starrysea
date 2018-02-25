package top.starrysea.params.responsibility;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class MutiPartFileArrayHandler extends ParamsHandler {

	@Override
	public String handleRequest(Object object) {
		if (object instanceof MultipartFile[]) {
			ArrayList<String> list = new ArrayList<>();
			MultipartFile[] files = (MultipartFile[]) object;
			for (MultipartFile file : files) {
				String params = "{\"originalFilename\":" + file.getOriginalFilename() + ",\"contentType\":"
						+ file.getContentType() + ",\"size\"" + file.getSize() + "}";
				list.add(params);
			}

			return files.getClass().getSimpleName() + list.toString();
		}
		return nextHandler.handleRequest(object);
	}

}
