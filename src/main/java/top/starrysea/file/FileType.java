package top.starrysea.file;

public enum FileType {

	IMG("FFD8FF", "89504E47", "47494638"), PDF("255044462D312E");

	private String[] magicNums;

	private FileType(String... magicNums) {
		this.magicNums = magicNums;
	}

	public boolean contains(String targetMagicNum) {
		for (String magicNum : magicNums) {
			if (targetMagicNum.startsWith(magicNum)) {
				return true;
			}
		}
		return false;
	}
}
