package top.starrysea.security;

public interface SecurityAlgorithm {
	String encrypt(String info);

	String decrypt(String encryptInfo);

	String getPublicKey();

	void setKey(String key);
}
