package com.qitai.utils;

import com.qitai.utils.exception.ExceptionUtil;
import com.qitai.utils.exception.ServiceException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类：摘要加密、对称加解密、非对称加解密
 * 
 * @author HuangLeibing
 *
 */
public final class EncryptUtil
{

	/**
	 * MD5加密
	 *
	 * @param str
	 * @return
	 */
	public static String encryptMD5(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			ExceptionUtil.log(e);
			e.printStackTrace();
			return null;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuilder md5StrBuff = new StringBuilder();
		for (byte b : byteArray) {
			String hexString = Integer.toHexString(0xFF & b);
			if (hexString.length() == 1) {
				md5StrBuff.append("0").append(hexString);
			} else {
				md5StrBuff.append(hexString);
			}
		}
		return md5StrBuff.toString();
	}

	/**
	 * MD5加密
	 * 
	 * @param input 要加密的字符串
	 * @return 如果成功，返回加密串；否则返回null
	 */
	public final static byte[] MD5(String input)
	{
		try
		{
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			return mdInst.digest(input.getBytes("utf-8"));
		}
		catch(Exception e)
		{
			ExceptionUtil.log(e);
			return null;
		}
	}
	
	/**
	 * sha1加密
	 * @param input 要加密的字符串
	 * @return 如果成功，返回加密串；否则返回null
	 */
	public final static byte[] Sha1(String input)
	{
		try
		{
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			return mDigest.digest(input.getBytes("utf-8"));
		}
		catch(Exception e)
		{
			ExceptionUtil.log(e);
			return null;
		}
	}

	/**
	 * 对称加密数据
	 * 
	 * @param input
	 * @param sPwd
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static byte[] AesEncrypt(byte[] input, String sPwd) throws UnsupportedEncodingException
	{
		return AesCipher(input,sPwd.getBytes("UTF-8"),true);
	}
	public static byte[] AesEncrypt(byte[] input, byte[] sPwd)
	{
		return AesCipher(input,sPwd,true);
	}
	/**
	 * 对称解密数据
	 * @param input
	 * @param sPwd
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static byte[] AesDecrypt(byte[] input, String sPwd) throws UnsupportedEncodingException
	{
		return AesCipher(input,sPwd.getBytes("UTF-8"),false);
	}
	public static byte[] AesDecrypt(byte[] input, byte[] sPwd)
	{
		return AesCipher(input,sPwd,false);
	}
	
	private static byte[] AesCipher(byte[] input,byte[] password,boolean encrypt)
	{
		try{
			byte[] pwdPadding=new byte[16];
			int iLen=16;
			if(password.length<16)
				iLen=password.length;
			System.arraycopy(password, 0, pwdPadding, 0, iLen);
			for(;iLen<16;++iLen)
				pwdPadding[iLen]=(byte)(0x2a+iLen);
			SecretKeySpec key = new SecretKeySpec(pwdPadding, "AES");
	
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			if(encrypt)// 初始化ENCRYPT_MODE 加密
				cipher.init(Cipher.ENCRYPT_MODE, key);
			else
				cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(input);
		}catch(Exception ex){
			ExceptionUtil.log(ex);
			throw new ServiceException("加密出错"+ex.getMessage(),ex);
		}
	}

}
