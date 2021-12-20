package com.qitai.utils;

import com.google.common.base.Splitter;
import com.qitai.utils.CheckUtil;
import com.qitai.utils.exception.ExceptionUtil;
import com.qitai.utils.exception.ServiceException;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Iterator;

/**
 * 使用Jdk的RSA算法加解密
 * @author dennis
 *
 */
public class CreateRSAKey {

	private final String KEY_ALGORITHM = "RSA";

	/**
	 * 根据base64按照特定的格式将RSAPublicKey转换成字符串.
	 */
	private String publicKey;

	/**
	 * 根据base64按照特定的格式将RSAPrivateKey转换成字符串.
	 */
	private String privateKey;

	/**
	 * 使用已有公钥私钥初始化
	 * @param pubKey
	 * @param privKey
	 */
	public CreateRSAKey(String pubKey,String privKey)
	{
		if(CheckUtil.isNullorEmpty(pubKey) && CheckUtil.isNullorEmpty(privKey))
			throw new ServiceException("初始化RSA公钥、私钥不能都为空");
		publicKey=pubKey;
		privateKey=privKey;
	}

	/**
	 * 生成公钥私钥构造函数，用密码对私钥进行加密。
	 *
	 * @param password 密码
	 */
	public CreateRSAKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey localPublicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey localPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

		String publicModulus = Base64.getEncoder().encodeToString(localPublicKey.getModulus().toByteArray());
		String publicExponent = Base64.getEncoder().encodeToString(localPublicKey.getPublicExponent().toByteArray());
		this.publicKey = publicModulus + "#" + publicExponent;

		String privateModulus = Base64.getEncoder().encodeToString(localPrivateKey.getModulus().toByteArray());
		String privateExponent = Base64.getEncoder().encodeToString(localPrivateKey.getPrivateExponent().toByteArray());
		this.privateKey = privateModulus + "#" + privateExponent;
	}

	/**
	 * 获取公钥.
	 *
	 * @return 公钥
	 */
	public String getPublicKey() {
		return this.publicKey;
	}

	/**
	 * 获取私钥.
	 *
	 * @return 私钥
	 */
	public String getPrivateKey() {
		return this.privateKey;
	}

	/**
	 * 使用公钥或私钥加密，如果私钥为空，则用公钥加密。
	 * 注：公钥、私钥不能同时为null
	 * @param input
	 * @return
	 */
	public byte[] Encrypt(byte[] input)
	{
		return Encrypt(input,!CheckUtil.isNullorEmpty(this.privateKey));
	}
	
	/**
	 * 使用公钥或私钥加密
	 * @param input
	 * @param byPrivate
	 * @return
	 */
	public byte[] Encrypt(byte[] input,boolean byPrivate)
	{
		try{
		String key=this.privateKey;
		if(!byPrivate) key=this.publicKey;
		// 解析key成modulus和privateExponent
		Iterator<String> keys = Splitter.on('#').trimResults().split(key)
				.iterator();
		BigInteger modulus = new BigInteger(
				Base64.getDecoder().decode(keys.next()));
		BigInteger pExponent = new BigInteger(
				Base64.getDecoder().decode(keys.next()));

		// 取得密钥
		Key LocalKey;
		KeyFactory keyFac = KeyFactory.getInstance("RSA");
		if(byPrivate){
			RSAPrivateKeySpec privateKey = new RSAPrivateKeySpec(modulus,pExponent);
			LocalKey = keyFac.generatePrivate(privateKey);
		}else{
			RSAPublicKeySpec publicKey = new RSAPublicKeySpec(modulus, pExponent);
			LocalKey = keyFac.generatePublic(publicKey);
		}
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFac.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, LocalKey);

		return cipher.doFinal(input);
		}catch(Exception ex){
			ExceptionUtil.log(ex);
			throw new ServiceException("加密异常:"+ex.getMessage(),ex);
		}
	}
	
	/**
	 * 使用公钥或私钥解密，如果公钥为空，则用私钥加密。
	 * 注：公钥、私钥不能同时为null
	 * @param input
	 * @return
	 */
	public byte[] Decrypt(byte[] input)
	{
		return Decrypt(input,!CheckUtil.isNullorEmpty(this.publicKey));
	}
	/**
	 * 使用公钥或私钥解密
	 * @param input
	 * @param byPublic
	 * @return
	 */
	public byte[] Decrypt(byte[] input,boolean byPublic)
	{
		try{
		String key=this.publicKey;
		if(!byPublic) key=this.privateKey;
		// 解析key成modulus和publicExponent
		Iterator<String> keys = Splitter.on('#').trimResults().split(key)
				.iterator();
		BigInteger modulus = new BigInteger(
				Base64.getDecoder().decode(keys.next()));
		BigInteger pExponent = new BigInteger(
				Base64.getDecoder().decode(keys.next()));

		// 取得密钥
		Key LocalKey;
		KeyFactory keyFac = KeyFactory.getInstance("RSA");
		if(byPublic){
			RSAPublicKeySpec publicKey = new RSAPublicKeySpec(modulus, pExponent);
			LocalKey = keyFac.generatePublic(publicKey);
		}else{
			RSAPrivateKeySpec privateKey = new RSAPrivateKeySpec(modulus,pExponent);
			LocalKey = keyFac.generatePrivate(privateKey);
		}
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFac.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, LocalKey);

		return cipher.doFinal(input);
		}catch(Exception ex){
			ExceptionUtil.log(ex);
			throw new ServiceException("解密异常:"+ex.getMessage(),ex);
		}
	}
}
