package top.starrysea.params.strategy;

import org.springframework.web.multipart.MultipartFile;

public class MutipartFileToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		MultipartFile file = (MultipartFile) object;
		return file.getClass().getSimpleName() + "{\"originalFilename\":" + file.getOriginalFilename()
				+ ",\"contentType\":" + file.getContentType() + ",\"size\":" + file.getSize() + "}";
	}

}
