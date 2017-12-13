package top.starrysea.file;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;

@Component
public class FileUtil {

	@Value("${ss.fileroot}")
	private String fileRoot;

	private FileUtil() {
	}

	public String saveFile(MultipartFile file, FileCondition fileCondition) throws IOException {
		if (file == null || file.isEmpty())
			throw new NullPointerException("文件为空");
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		if (!isRightFileType(fileType, fileCondition.getFileType()))
			throw new IllegalArgumentException("文件类型不正确");
		Double fileSize = (double) file.getSize() / (double) (1024 * 1024);
		if (fileSize.compareTo(fileCondition.getFileSize()) > 0)
			throw new IllegalArgumentException("文件太大");
		String originFileName = fileCondition.getFileNamePrefix() + Common.getCharId(5) + "." + fileType;
		String filePath = fileRoot + originFileName;
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(filePath));
		} catch (IOException e) {
			throw e;
		}
		return "/" + originFileName;
	}

	private boolean isRightFileType(String sourceFileType, FileType fileType) {
		switch (fileType) {
		case IMG:
			return sourceFileType.equals("png") || sourceFileType.equals("jpg") || sourceFileType.equals("jpeg");
		case PDF:
			return sourceFileType.equals("pdf");
		}
		return false;
	}

}
