package top.starrysea.security;

public interface SecurityAlgorithm {
	String encrypt(String info);

	String decrypt(String encryptInfo);

}
