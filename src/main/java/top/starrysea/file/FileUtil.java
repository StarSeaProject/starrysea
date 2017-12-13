package top.starrysea.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;

@Component
public class FileUtil {

	@Value("${ss.fileroot}")
	private String fileRoot;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FileUtil() {
	}

	public String saveFile(MultipartFile file, FileCondition fileCondition) throws IOException {
		if (file == null || file.isEmpty())
			throw new NullPointerException("文件为空");
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
		if (!isRightFileType(fileType, fileCondition.getFileType()))
			throw new IllegalArgumentException("文件类型不正确");
		Double fileSize = (double) file.getSize() / (double) (1024 * 1024);
		if (fileSize.compareTo(fileCondition.getFileSize()) > 0)
			throw new IllegalArgumentException("文件太大");
		String originFileName = fileCondition.getFileNamePrefix() + Common.getCharId(5) + "." + fileType;
		String nowDate = sdf.format(new Date());
		String filePathName = getFilePathName(fileCondition.getFileType());
		String returnFilePath = filePathName + File.separator + nowDate + File.separator + originFileName;
		String filePath = fileRoot + returnFilePath;
		String targetFileName = fileRoot + filePathName + File.separator + nowDate + File.separator;
		if (!new File(targetFileName).exists())
			new File(targetFileName).mkdirs();
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(filePath));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return returnFilePath;
	}

	private boolean isRightFileType(String sourceFileType, FileType fileType) {
		if (fileType == FileType.IMG) {
			return sourceFileType.equals("png") || sourceFileType.equals("jpg") || sourceFileType.equals("jpeg");
		} else if (fileType == FileType.PDF) {
			return sourceFileType.equals("pdf");
		}
		return false;
	}

	private String getFilePathName(FileType fileType) {
		if (fileType == FileType.IMG) {
			return "img";
		} else if (fileType == FileType.PDF) {
			return "pdf";
		} else
			return "";
	}
}
