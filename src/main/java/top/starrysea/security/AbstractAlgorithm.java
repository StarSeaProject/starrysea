package top.starrysea.security;

public abstract class AbstractAlgorithm implements SecurityAlgorithm {

	@Override
	public String encrypt(String info) {
		return null;
	}

	@Override
	public abstract String decrypt(String encryptInfo);

	@Override
	public String getPublicKey() {
		return null;
	}

	@Override
	public void setKey(String key) {
	}

}
