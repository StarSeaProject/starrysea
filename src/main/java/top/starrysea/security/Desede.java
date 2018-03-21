package top.starrysea.security;

import static top.starrysea.common.Const.CHARSET;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import top.starrysea.common.Common;

@Component("desede")
public class Desede implements SecurityAlgorithm {

	private Logger logger = Logger.getLogger(this.getClass());
	// 初始秘钥
	private String key = "YeahTiger!NoTiger!iyaiyaiyaiyaYeahTiger!";
	// 加解密向量,不要改
	private static final String IV = "12345678";

	@Scheduled(cron = "0 0 3 * * ?")
	public void setKey() {
		this.key = Common.getCharId(32);
	}

	@Override
	public String encrypt(String info) {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(CHARSET));
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(IV.getBytes(CHARSET));
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(info.getBytes(CHARSET));
			return Common.byte2base64(encryptData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String decrypt(String encryptInfo) {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(CHARSET));
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/NoPadding");
			IvParameterSpec ips = new IvParameterSpec(IV.getBytes(CHARSET));
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] decryptData = cipher.doFinal(Common.base642byte(encryptInfo));
			return new String(decryptData, CHARSET).trim();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}
}
