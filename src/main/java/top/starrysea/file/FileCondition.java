package top.starrysea.file;

public class FileCondition {

	private FileType fileType;
	private Double fileSize;
	private String fileNamePrefix;
	
	private FileCondition() {}
	
	public static FileCondition of(FileType fileType,String fileNamePrefix) {
		FileCondition fileCondition=new FileCondition();
		fileCondition.setFileType(fileType);
		fileCondition.setFileNamePrefix(fileNamePrefix);
		return fileCondition;
	}
	
	public static FileCondition of(FileType fileType,double fileSize,String fileNamePrefix) {
		FileCondition fileCondition=new FileCondition();
		fileCondition.setFileType(fileType);
		fileCondition.setFileSize(fileSize);
		fileCondition.setFileNamePrefix(fileNamePrefix);
		return fileCondition;
	}

	public FileType getFileType() {
		return fileType;
	}

	private void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Double getFileSize() {
		return fileSize;
	}

	private void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileNamePrefix() {
		return fileNamePrefix;
	}

	private void setFileNamePrefix(String fileNamePrefix) {
		this.fileNamePrefix = fileNamePrefix;
	}
	
}
