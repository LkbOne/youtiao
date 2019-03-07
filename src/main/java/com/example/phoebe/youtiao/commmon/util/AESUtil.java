package com.example.phoebe.youtiao.commmon.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author doggy
 *         Created on 2017-01-02.
 */
public class AESUtil {
	private static final String ALG = "AES";
	private static final String STRING_ENCODE = "UTF-8";

	public static String randomKeyByPassword(String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALG);
			kgen.init(128, new SecureRandom(password.getBytes(STRING_ENCODE)));
			SecretKey secretKey = kgen.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			return Base64Util.bytesToString(keyBytes);
		} catch (Exception e) {
			throw new RuntimeException("NH");
		}
	}

	/**
	 * @param key:     The key must be generate by method AESUtil#randomKeyByPassword.
	 * @param content: The content should be UTF8 encode.
	 * @return encoded string.
	 */
	public static String encode(String key, String content) {
		try {
			SecretKey secretKey = getSecretKey(key);
			Cipher cipher = Cipher.getInstance(ALG);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] bs = cipher.doFinal(content.getBytes(STRING_ENCODE));
			return Base64Util.bytesToString(bs);
		} catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException("AES alg not found");
		} catch (UnsupportedEncodingException uee) {
			throw new RuntimeException("UTF-8 not supported");
		} catch (NoSuchPaddingException nspe) {
			throw new RuntimeException("No such paddign");
		} catch (InvalidKeyException ike) {
			throw new RuntimeException("Invalid key");
		} catch (Exception e) {
			throw new RuntimeException("E");
		}
	}
	
	public static <T> String objectEncode(String aesKey, T obj){
		String json = GsonUtil.getGson().toJson(obj);
		return AESUtil.encode(aesKey, json);
	}
	
	public static <T> T objectDecode(String aesKey, String encodeJson, Class<T> clazz){
		String json = AESUtil.decode(aesKey, encodeJson);
		return GsonUtil.getGson().fromJson(json, clazz);
	}

	/**
	 * @param key:The     should be same with encode key.
	 * @param encoded:The encoded content.
	 * @return decoded string.
	 */
	public static String decode(String key, String encoded) {
		try {
			SecretKey secretKey = getSecretKey(key);
			Cipher cipher = Cipher.getInstance(ALG);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] bs = cipher.doFinal(Base64Util.stringToBytes(encoded));
			return new String(bs, STRING_ENCODE);
		} catch (Exception e) {
			throw new RuntimeException("E", e);
		}
	}

	private static SecretKey getSecretKey(String key) {
		byte[] keyBytes = Base64Util.stringToBytes(key);
		return new SecretKeySpec(keyBytes, ALG);
	}

	public static void main(String[] args) {
		String key = randomKeyByPassword("phoebeYouTiao");
		String content = "Hello";
		String encoded = encode(key, content);
		String decoded = decode(key, encoded);

		System.out.println("key: "+key);
		System.out.println("encoded: "+encoded);
		System.out.println("decoded: "+decoded);
	}
}
