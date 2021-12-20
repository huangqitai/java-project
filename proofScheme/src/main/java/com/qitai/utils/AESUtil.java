package com.qitai.utils;
import com.qitai.utils.exception.ExceptionUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author 胡现荣
 * @date 2020-10-21
 * 只提供粤省事AES加密
 */
public class AESUtil {
	public static void main(String[] args) {
		//密钥
		String password = "b951ce3e8f5c3c3e";
		//字符内容
		String str = "";
		String tempContent1 = com.qitai.utils.AESUtil.decryptAes(str, password);
		System.out.println("解密后：\n" + tempContent1);
		String jm = encryptAes(str, password);
		System.out.println("加密后：\n" + jm);
	}

	/**
	 * 解密
	 * @param  content 解密字符串
	 * @param  key 密钥串
	 * @return String
	 */
	public static String decryptAes(String content, String key) {
	        try {
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
	            // "算法/模式/补码方式"
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	            //解密后是16进制
	            return new String(cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(content))), StandardCharsets.UTF_8);
	        } catch (Exception e) {
				ExceptionUtil.log(e);
	            e.printStackTrace();
	        }
	        return content;
	}
	    
	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr 字符串
	 * @return byte[]
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 加密算法
	 * @param  content 加密的字符串
	 * @param  key 解密的key
	 * @return String
	 */
	public static String encryptAes(String content, String key) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] result = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
			return parseByte2HexStr(result);
		} catch (Exception e) {
			ExceptionUtil.log(e);
		  e.printStackTrace();
		}
		return content;
	}
	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf 字节
	 * @return String
	 */
	public static String parseByte2HexStr(byte[] buf) {
		StringBuilder sb = new StringBuilder();
		for (byte b : buf) {
			String hex = Integer.toHexString(b & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
   }
}
