package top.starrysea.security;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import top.starrysea.common.Common;
@Component("desede")
public class Desede extends AbstractAlgorithm {

	private Logger logger = Logger.getLogger(this.getClass());
	private  static final String KEY="YeahTiger!NoTiger!iyaiyaiyaiyaYeahTiger!";
	// 加解密向量,不要改
	private  static final String IV = "12345678";
	
	@Override
	public String encrypt(String info) {
		try{
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(KEY.getBytes("UTF-8"));
		SecretKeyFactory keyfactory = SecretKeyFactory
				.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(IV.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData=cipher.doFinal(info.getBytes("UTF-8"));
		return Common.byte2base64(encryptData);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String decrypt(String encryptInfo) {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(KEY.getBytes("UTF-8"));
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/NoPadding");
			IvParameterSpec ips = new IvParameterSpec(IV.getBytes("UTF-8"));
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] decryptData = cipher.doFinal(Common.base642byte(encryptInfo));
			return new String(decryptData, "UTF-8").trim();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}
}

