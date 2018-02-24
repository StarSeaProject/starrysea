package top.starrysea.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;

@Component
public class FileUtil implements InitializingBean {

	@Value("${ss.fileroot}")
	private String fileRoot;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static List<String> cucuImgNames;

	public String saveFile(MultipartFile file, FileCondition fileCondition) throws IOException {
		if (file == null || file.isEmpty())
			throw new NullPointerException("文件为空");
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
		if (!fileCondition.getFileType().contains(bytesToHex(file.getBytes())))
			throw new IllegalArgumentException("文件类型不正确");
		Double fileSize = (double) file.getSize() / (double) (1024 * 1024);
		if (fileCondition.getFileSize() != null && fileSize.compareTo(fileCondition.getFileSize()) > 0)
			throw new IllegalArgumentException("文件太大");
		String originFileName = fileCondition.getFileNamePrefix() + Common.getCharId(5) + "." + fileType;
		String nowDate = sdf.format(new Date());
		String filePathName = getFilePathName(fileCondition.getFileType());
		String filePath = fileRoot + filePathName + File.separator + nowDate + File.separator + originFileName;
		String targetFileName = fileRoot + filePathName + File.separator + nowDate + File.separator;
		if (!new File(targetFileName).exists())
			new File(targetFileName).mkdirs();
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(filePath));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return File.separator + nowDate + File.separator + originFileName;
	}

	/** 将字节数组转换成16进制字符串 */
	public String bytesToHex(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	private String getFilePathName(FileType fileType) {
		if (fileType == FileType.IMG) {
			return "img";
		} else if (fileType == FileType.PDF) {
			return "pdf";
		} else
			return "";
	}

	public static String getCucuImg() {
		if (cucuImgNames == null)
			return "";
		Random random = new Random();
		return cucuImgNames.get(random.nextInt(cucuImgNames.size()));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		File cucu = new File(fileRoot + "img/cucu");
		if (cucu.exists()) {
			cucuImgNames = Arrays.asList(cucu.list());
		}
	}

}
