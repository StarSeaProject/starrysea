package top.starrysea.params.strategy;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class MutipartFileArrayToString implements ChangeToString {

	@Override
	public String paramToString(Object object) {
		ArrayList<String> list = new ArrayList<String>();
		MultipartFile[] files = (MultipartFile[]) object;
		for (MultipartFile file : files) {
			String params = "{\"originalFilename\":" + file.getOriginalFilename() + ",\"contentType\":"
					+ file.getContentType() + ",\"size\"" + file.getSize() + "}";
			list.add(params);
		}

		return list.toString();
	}

}
