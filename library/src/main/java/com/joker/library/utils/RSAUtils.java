///**
//*
//* @author joker 
//* @date 创建时间：2018年5月18日 下午1:00:09
//* 
//*/
//package com.joker.library.utils;
//
//import java.security.Key;
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.Signature;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.UUID;
//import java.util.Map.Entry;
//
//import javax.crypto.Cipher;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.collections.CollectionUtils;
//
//import net.sf.json.util.NewBeanInstanceStrategy;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年5月18日 下午1:00:09
// */
//public class RSAUtils
//{
//	// 定义加密方式
//	public static final String KEY_RSA = "RSA";
//	// 定义公钥关键词
//	public static final String KEY_RSA_PUBLICKEY = "RSAPublicKey";
//	// 定义私钥关键词
//	public static final String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";
//	// 定义签名算法
//	private final static String KEY_RSA_SIGNATURE = "MD5withRSA";
//
//	/**
//	 * 生成公私密钥对
//	 */
//	public static Map<String, Object> init()
//	{
//		Map<String, Object> map = null;
//		try
//		{
//			KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);
//			// 设置密钥对的bit数，越大越安全，但速度减慢，一般使用512或1024
//			generator.initialize(1024);
//			KeyPair keyPair = generator.generateKeyPair();
//			// 获取公钥
//			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//			// 获取私钥
//			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//			// 将密钥对封装为Map
//			map = new HashMap<String, Object>();
//			map.put(KEY_RSA_PUBLICKEY, publicKey);
//			map.put(KEY_RSA_PRIVATEKEY, privateKey);
//		} catch (NoSuchAlgorithmException e)
//		{
//			e.printStackTrace();
//		}
//		return map;
//	}
//
//	/**
//	 * 获取Base64编码的公钥字符串
//	 */
//	public static String getPublicKey(Map<String, Object> map)
//	{
//		String str = "";
//		Key key = (Key) map.get(KEY_RSA_PUBLICKEY);
//		str = encryptBase64(key.getEncoded());
//		return str;
//	}
//
//	/**
//	 * 获取Base64编码的私钥字符串
//	 */
//	public static String getPrivateKey(Map<String, Object> map)
//	{
//		String str = "";
//		Key key = (Key) map.get(KEY_RSA_PRIVATEKEY);
//		str = encryptBase64(key.getEncoded());
//		return str;
//	}
//
//	/**
//	 * BASE64 解码
//	 *
//	 * @param key
//	 *            需要Base64解码的字符串
//	 * @return 字节数组
//	 */
//	public static byte[] decryptBase64(String key)
//	{
////		return Base64.getDecoder().decode(key);
//		return Base64.decodeBase64(key);
//	}
//
//	/**
//	 * BASE64 编码
//	 *
//	 * @param key
//	 *            需要Base64编码的字节数组
//	 * @return 字符串
//	 */
//	public static String encryptBase64(byte[] key)
//	{
////		return new String(Base64.getEncoder().encode(key));
//		return new String(Base64.encodeBase64(key));
//	}
//
//	/**
//	 * 公钥加密
//	 *
//	 * @param encryptingStr
//	 * @param publicKeyStr
//	 * @return
//	 */
//	public static String encryptByPublic(String encryptingStr, String publicKeyStr)
//	{
//		try
//		{
//			// 将公钥由字符串转为UTF-8格式的字节数组
//			byte[] publicKeyBytes = decryptBase64(publicKeyStr);
//			// 获得公钥
//			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//			// 取得待加密数据
//			byte[] data = encryptingStr.getBytes("UTF-8");
//			KeyFactory factory;
//			factory = KeyFactory.getInstance(KEY_RSA);
//			PublicKey publicKey = factory.generatePublic(keySpec);
//			// 对数据加密
//			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
//			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			// 返回加密后由Base64编码的加密信息
//			return encryptBase64(cipher.doFinal(data));
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	/**
//	 * 私钥解密
//	 *
//	 * @param encryptedStr
//	 * @param privateKeyStr
//	 * @return
//	 */
//	public static String decryptByPrivate(String encryptedStr, String privateKeyStr)
//	{
//		try
//		{
//			// 对私钥解密
//			byte[] privateKeyBytes = decryptBase64(privateKeyStr);
//			// 获得私钥
//			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//			// 获得待解密数据
//			byte[] data = decryptBase64(encryptedStr);
//			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
//			PrivateKey privateKey = factory.generatePrivate(keySpec);
//			// 对数据解密
//			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
//			cipher.init(Cipher.DECRYPT_MODE, privateKey);
//			// 返回UTF-8编码的解密信息
//			return new String(cipher.doFinal(data), "UTF-8");
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	
//	public static String encryptByPrivate(String encrytString,byte[] privateKeyBytes)
//	{
//		return encryptByPrivate(encrytString, new String(privateKeyBytes));
//	}
//	/**
//	 * 私钥加密
//	 *
//	 * @param encryptingStr
//	 * @param privateKeyStr
//	 * @return
//	 */
//	public static String encryptByPrivate(String encryptingStr, String privateKeyStr)
//	{
//		try
//		{
//			byte[] privateKeyBytes = decryptBase64(privateKeyStr);
//			// 获得私钥
//			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//			// 取得待加密数据
//			byte[] data = encryptingStr.getBytes("UTF-8");
//			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
//			PrivateKey privateKey = factory.generatePrivate(keySpec);
//			// 对数据加密
//			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
//			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//			// 返回加密后由Base64编码的加密信息
//			return encryptBase64(cipher.doFinal(data));
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//	public static String decryptByPublic(String encryptStr,byte[] publicKeyStr)
//	{
//		return decryptByPublic(encryptStr, new String(publicKeyStr));
//	}
//	/**
//	 * 公钥解密
//	 *
//	 * @param encryptedStr
//	 * @param publicKeyStr
//	 * @return
//	 */
//	public static String decryptByPublic(String encryptedStr, String publicKeyStr)
//	{
//			// 对公钥解密
//			byte[] publicKeyBytes = decryptBase64(publicKeyStr);
//			// 取得公钥
//			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//			// 取得待加密数据
//			byte[] data = decryptBase64(encryptedStr);
//			try
//			{
//				KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
//				PublicKey publicKey = factory.generatePublic(keySpec);
//				// 对数据解密
//				Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
//				cipher.init(Cipher.DECRYPT_MODE, publicKey);
//				// 返回UTF-8编码的解密信息
//				return new String(cipher.doFinal(data), "UTF-8");
//
//			} catch (Exception e)
//			{
//				return null;
//			}
//	}
//	
//	public static String sign(String encryptedStr, byte[] bytes)
//	{
//		return sign(encryptedStr, new String(bytes));
//	}
//	/**
//	 * 用私钥对加密数据进行签名
//	 *
//	 * @param encryptedStr
//	 * @param privateKey
//	 * @return
//	 */
//	public static String sign(String encryptedStr, String privateKey)
//	{
//		String str = "";
//		try
//		{
//			// 将私钥加密数据字符串转换为字节数组
//			byte[] data = encryptedStr.getBytes();
//			// 解密由base64编码的私钥
//			byte[] bytes = decryptBase64(privateKey);
//			// 构造PKCS8EncodedKeySpec对象
//			PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
//			// 指定的加密算法
//			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
//			// 取私钥对象
//			PrivateKey key = factory.generatePrivate(pkcs);
//			// 用私钥对信息生成数字签名
//			Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
//			signature.initSign(key);
//			signature.update(data);
//			str = encryptBase64(signature.sign());
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return str;
//	}
//
//	/**
//	 * 校验数字签名
//	 *
//	 * @param encryptedStr 原始数据
//	 * @param publicKey	公钥
//	 * @param sign	签名数据
//	 * @return 校验成功返回true，失败返回false
//	 */
//	public static boolean verify(String primitiveStr, String publicKey, String sign)
//	{
//		boolean flag = false;
//		try
//		{
//			// 将私钥加密数据字符串转换为字节数组
//			byte[] data = primitiveStr.getBytes();
//			// 解密由base64编码的公钥
//			byte[] bytes = decryptBase64(publicKey);
//			// 构造X509EncodedKeySpec对象
//			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
//			// 指定的加密算法
//			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
//			// 取公钥对象
//			PublicKey key = factory.generatePublic(keySpec);
//			// 用公钥验证数字签名
//			Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
//			signature.initVerify(key);
//			signature.update(data);
//			flag = signature.verify(decryptBase64(sign));
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return flag;
//	}
//
//	public static void main(String[] args)
//	{
//		 Map<String, Object> map = new HashMap<String, Object>();
//		 map = RSAUtils.init();
//		 System.out.println("公钥：" + RSAUtils.getPublicKey(map));
//		 System.out.println("私钥：" + RSAUtils.getPrivateKey(map));
////		 String publicKey = RSAUtils.getPublicKey(map);
////		 String privateKey = RSAUtils.getPrivateKey(map);
//		 String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMFWbEYvvKDrSbunjCawaL2dwCr+kuPnF8QR589qa0UgrNdDTI1WlrESEZXD+27U6WLwv9fOicKWmdchx+9h6R24iJnos7VHI6Wyy6zYnE+pbU+KTCwzsO3n+Pbe2q7AHrEHsGZp0y+HvXULha4mQRCv52fXdXNi2nCca1iHtGAQIDAQAB";
//		 
//		 String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIwVZsRi+8oOtJu6eMJrBovZ3AKv6S4+cXxBHnz2prRSCs10NMjVaWsRIRlcP7btTpYvC/186JwpaZ1yHH72HpHbiImeiztUcjpbLLrNicT6ltT4pMLDOw7ef49t7arsAesQewZmnTL4e9dQuFriZBEK/nZ9d1c2LacJxrWIe0YBAgMBAAECgYBjawz0dAue67DilQT/dlW/C29JuO8VEtwYA19P8P1/HHruNXsAzfVzN0T2arINcX5EuD8aOHwd2IZMZ6AxuxsV/Jusev61rxCI5XzWs7jIbdqbZCVG97S4mdnh2HtfHi63AC4NjdB2iC7lqgZxW14tVrRQvpzOsRoSNlfapVHGwQJBAL2u0ijT8tP3VnmGPg7jEr7ECE1unPZYM8JKdFwWuzbrnGJ8PeU3W3Z3HRCoCxY15JmntrO6FE2RZkjSzabutW8CQQC9D0wS73a/UrCB5Nto4V4Ywe2zC6PV5HB5MEkzq9TiL/5IsdQQgveD7Qzkf9NEIAkaPDN8sohQY4Wd76xdpmOPAkEAvVUq4SXuVDWmU1bzythNda0mvGOUuTABOgYnb2QhKwiy6HsrLQi8etsaX+3cYycYL2eArpUmfuaLOlVwgRf0cQJBALM7N1GUNy02BSN/hQou7XfnEaN124JBKOebASq9MfqkqRrdiz+ECYbGSjyteenRvQ9kFyOWVWhwkRGjT/MQdL0CQCi5PIsS8EO2a79OgZ3uUUGB1ay9/tsTUxpCvNTL3xl7VTmuk7FuU6jtGaHmKotT2hPHXByRNBhx+IR5ewW4b1Q=";
//		// 由前四行代码获得公、私密钥
//
////		 String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMFWbEYvvKDrSbunjCawaL2dwCr+kuPnF8QR589qa0UgrNdDTI1WlrESEZXD+27U6WLwv9fOicKWmdchx+9h6R24iJnos7VHI6Wyy6zYnE+pbU+KTCwzsO3n+Pbe2q7AHrEHsGZp0y+HvXULha4mQRCv52fXdXNi2nCca1iHtGAQIDAQAB";
//		 // String privateKey=
//		 //"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMFWbEYvvKDrSbunjCawaL2dwCr+kuPnF8QR589qa0UgrNdDTI1WlrESEZXD+27U6WLwv9fOicKWmdchx+9h6R24iJnos7VHI6Wyy6zYnE+pbU+KTCwzsO3n+Pbe2q7AHrEHsGZp0y+HvXULha4mQRCv52fXdXNi2nCca1iHtGAQIDAQAB";
////		 String privateKey ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIwVZsRi+8oOtJu6eMJrBovZ3AKv6S4+cXxBHnz2prRSCs10NMjVaWsRIRlcP7btTpYvC/186JwpaZ1yHH72HpHbiImeiztUcjpbLLrNicT6ltT4pMLDOw7ef49t7arsAesQewZmnTL4e9dQuFriZBEK/nZ9d1c2LacJxrWIe0YBAgMBAAECgYBjawz0dAue67DilQT/dlW/C29JuO8VEtwYA19P8P1/HHruNXsAzfVzN0T2arINcX5EuD8aOHwd2IZMZ6AxuxsV/Jusev61rxCI5XzWs7jIbdqbZCVG97S4mdnh2HtfHi63AC4NjdB2iC7lqgZxW14tVrRQvpzOsRoSNlfapVHGwQJBAL2u0ijT8tP3VnmGPg7jEr7ECE1unPZYM8JKdFwWuzbrnGJ8PeU3W3Z3HRCoCxY15JmntrO6FE2RZkjSzabutW8CQQC9D0wS73a/UrCB5Nto4V4Ywe2zC6PV5HB5MEkzq9TiL/5IsdQQgveD7Qzkf9NEIAkaPDN8sohQY4Wd76xdpmOPAkEAvVUq4SXuVDWmU1bzythNda0mvGOUuTABOgYnb2QhKwiy6HsrLQi8etsaX+3cYycYL2eArpUmfuaLOlVwgRf0cQJBALM7N1GUNy02BSN/hQou7XfnEaN124JBKOebASq9MfqkqRrdiz+ECYbGSjyteenRvQ9kFyOWVWhwkRGjT/MQdL0CQCi5PIsS8EO2a79OgZ3uUUGB1ay9/tsTUxpCvNTL3xl7VTmuk7FuU6jtGaHmKotT2hPHXByRNBhx+IR5ewW4b1Q=";
//		
//		 String str = UUID.randomUUID().toString();
//		 // 公钥加密，私钥解密
//		 String enStr1 = RSAUtils.encryptByPublic(str, publicKey);
//		 System.out.println("公钥加密后：" + enStr1);
//		 String deStr1 = RSAUtils.decryptByPrivate(enStr1, privateKey);
//		 System.out.println("私钥解密后：" + deStr1);
//		 // 私钥加密，公钥解密
//		 String enStr2 = RSAUtils.encryptByPrivate(str, privateKey);
//		 System.out.println("私钥加密后：" + enStr2);
//		 String deStr2 = RSAUtils.decryptByPublic(enStr2, publicKey);
//		 System.out.println("公钥解密后：" + deStr2);
//		 // 产生签名
//		 String sign = sign(enStr2, privateKey);
//		 System.out.println("签名:" + sign);
//		 // 验证签名:因为enStr2是由私钥生成的,所以验证的时候需要用公钥来验证
//		 boolean status = verify(enStr2, publicKey, sign);
//		 System.out.println("状态:" + status);
//	}
//}
