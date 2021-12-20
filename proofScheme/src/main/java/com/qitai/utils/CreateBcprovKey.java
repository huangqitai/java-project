package com.qitai.utils;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.exception.ExceptionUtil;
import com.qitai.utils.exception.ServiceException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 使用Bouncy Castle Provider的RSA算法加解密
 * @author dennis
 *
 */
public class CreateBcprovKey {

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
	public CreateBcprovKey(String pubKey,String privKey)
	{
		if(CheckUtil.isNullorEmpty(pubKey) && CheckUtil.isNullorEmpty(privKey))
			throw new ServiceException("初始化RSA公钥、私钥不能都为空");
		publicKey=pubKey;
		privateKey=privKey;
	}
	
	/**
	 * 生成公钥私钥构造函数
	 */
	public CreateBcprovKey() throws Exception
	{
    //生成密钥对
    RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();
    RSAKeyGenerationParameters rsaKeyGenerationParameters = new RSAKeyGenerationParameters(BigInteger.valueOf(3),
    		new SecureRandom(), 1024, 25);
    rsaKeyPairGenerator.init(rsaKeyGenerationParameters);//初始化参数
    AsymmetricCipherKeyPair keyPair = rsaKeyPairGenerator.generateKeyPair();

    //变字符串
    AsymmetricKeyParameter publicKey = (AsymmetricKeyParameter)keyPair.getPublic();//公钥
    SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey);
    ASN1Primitive asn1ObjectPublic = subjectPublicKeyInfo.toASN1Primitive();
    byte[] publicInfoByte = asn1ObjectPublic.getEncoded();
    
    AsymmetricKeyParameter privateKey = (AsymmetricKeyParameter)keyPair.getPrivate();//私钥
    PrivateKeyInfo privateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(privateKey);
    ASN1Primitive asn1ObjectPrivate = privateKeyInfo.toASN1Primitive();
    byte[] privateInfoByte = asn1ObjectPrivate.getEncoded();

    //这里可以将密钥对保存到本地
    final Base64.Encoder encoder64 = Base64.getEncoder();
    this.publicKey=encoder64.encodeToString(publicInfoByte);
    this.privateKey=encoder64.encodeToString(privateInfoByte);
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
      AsymmetricBlockCipher cipher = new RSAEngine();
      String key=this.privateKey;
      if(!byPrivate)key=this.publicKey;
      byte[] pInfoBytes=Base64.getDecoder().decode(key);
      
      AsymmetricKeyParameter pKey;
      ASN1Primitive pubKeyObj =ASN1Sequence.fromByteArray(pInfoBytes);
      if(byPrivate){
      	pKey = PrivateKeyFactory.createKey(PrivateKeyInfo.getInstance(pubKeyObj));
      }else{
      	pKey = PublicKeyFactory.createKey(SubjectPublicKeyInfo.getInstance(pubKeyObj));
      }

      cipher.init(true, pKey);
      return cipher.processBlock(input, 0, input.length);
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
			AsymmetricBlockCipher cipher = new RSAEngine();
      String key=this.publicKey;
      if(!byPublic)key=this.privateKey;
      byte[] pInfoBytes=Base64.getDecoder().decode(key);

      AsymmetricKeyParameter pKey;
      ASN1Primitive pubKeyObj =ASN1Sequence.fromByteArray(pInfoBytes);
      if(byPublic){
      	pKey = PublicKeyFactory.createKey(SubjectPublicKeyInfo.getInstance(pubKeyObj));
      }else{
      	pKey = PrivateKeyFactory.createKey(PrivateKeyInfo.getInstance(pubKeyObj));
      }

      cipher.init(false, pKey);//false表示解密
      return cipher.processBlock(input, 0, input.length);
		}catch(Exception ex){
			ExceptionUtil.log(ex);
			throw new ServiceException("解密异常:"+ex.getMessage(),ex);
		}
	}
}
