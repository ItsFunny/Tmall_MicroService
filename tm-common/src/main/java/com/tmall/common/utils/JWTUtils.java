/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月30日 下午10:05:25
* 
*/
package com.tmall.common.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

import com.joker.library.utils.JsonUtil;
import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 私钥加密,公钥解密,不可倒置
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月30日 下午10:05:25
 */
public class JWTUtils
{

	private PrivateKey authPrivateKey;
	private PrivateKey ssoPrivateKey;

	private PublicKey authPublicKey;
	private PublicKey ssoPublicKey;

	private byte[] privateKeyBytes;
	private byte[] publicKeyBytes;

	private byte[] ssoPrivateKeyBytes;
	private byte[] ssoPublicKeyBytes;

	public void initKeys() throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		this.authPrivateKey = keyFactory.generatePrivate(privateKeySpec);

		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		this.authPublicKey = keyFactory.generatePublic(publicKeySpec);

		X509EncodedKeySpec ssoPublicKeySpec = new X509EncodedKeySpec(ssoPublicKeyBytes);
		this.ssoPublicKey = keyFactory.generatePublic(ssoPublicKeySpec);

		PKCS8EncodedKeySpec ssoPrivateKeySpec = new PKCS8EncodedKeySpec(ssoPrivateKeyBytes);
		this.ssoPrivateKey = keyFactory.generatePrivate(ssoPrivateKeySpec);

	}

	public String buildByAuthPrivateKey(AuthTokenDTO authTokenDTO, Integer expire)
	{
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setIssuer("marco").setSubject("token")
				.signWith(signatureAlgorithm, authPrivateKey);
		Long endTime = System.currentTimeMillis() + expire;
		authTokenDTO.setInvalidTime(endTime);
		String json = JsonUtils.obj2Json(authTokenDTO);
		builder.claim("Authorization", json);
		return builder.compact();
	}

	public String buildByAuthPrivateKey(AuthTokenDTO authTokenDTO)
	{
		//token 的有效期为1天
		return buildByAuthPrivateKey(authTokenDTO, 1000 * 60 * 60 * 24);
	}

	public String buildBySSOPrivateKey(Object authTokenDTO, Integer expire)
	{
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setIssuer("marco").setSubject("token")
				.signWith(signatureAlgorithm, ssoPrivateKey);
		Long endTime = System.currentTimeMillis() + expire;
//		authTokenDTO.setInvalidTime(endTime);
		String json = JsonUtil.obj2Json(authTokenDTO);
		builder.claim("Authorization", json);
		builder.claim(AuthConstant.INVALID_TIME, endTime);
		return builder.compact();
	}

	public String buildBySSOPrivateKey(Object authTokenDTO)
	{
		// 5分钟
		return buildBySSOPrivateKey(authTokenDTO, 1000 * 60 * 5);
	}
	
	public String parseBySSOPublicKeyReturnJson(String token)
	{
		Claims claims = null;
		try
		{
			claims = Jwts.parser().setSigningKey(ssoPublicKey).parseClaimsJws(token).getBody();
			String json = claims.get("Authorization").toString();
			return json;
		}catch (Exception e) {
			return null;
		}
	}

	public AuthTokenDTO parseBySSOPublicKey(String token)
	{
		Claims claims = null;
		try
		{
			claims = Jwts.parser().setSigningKey(ssoPublicKey).parseClaimsJws(token).getBody();
			String json = claims.get("Authorization").toString();
			String invalidTime=claims.get(AuthConstant.INVALID_TIME).toString();
			if (StringUtils.isEmpty(json)||StringUtils.isEmpty(invalidTime))
			{
				return null;
			}
			AuthTokenDTO tokenDTO = JsonUtils.json2Object(json, AuthTokenDTO.class);
			tokenDTO.setInvalidTime(Long.parseLong(invalidTime));
			return tokenDTO;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public AuthTokenDTO parseByAuthPublicKey(String token)
	{
		Claims claims = null;
		try
		{
			claims = Jwts.parser().setSigningKey(authPublicKey).parseClaimsJws(token).getBody();
			String json = claims.get(AuthConstant.AUTH_HEADER).toString();
			if (StringUtils.isEmpty(json))
			{
				return null;
			}
			return JsonUtils.json2Object(json, AuthTokenDTO.class);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
//	public boolean parseAndCheckInvalid(String token)
//	{
//		
//	}

	public static String buildJWTRS256() throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
		// 读取私钥
		String key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJbexuKoyLCRfHZB"
				+ "Z4w+92V6P/9lgZUE20uX8YO2L+rO7MqTW0HiVXRqWCIGugODujjVUXNmd6rV2hKE"
				+ "YqPPR/UgmE4tf14jgpUAwl5N15OHbU6v9g4PI+FBCCV8Shg+/LIr8NRrVU1I8jjX"
				+ "yT+xPdt+6Z2s5vuo/uT9IEj/SEezAgMBAAECgYAX8/S1pcB/MAoHqGitPo3+wYMJ"
				+ "pG8HXscGYHBKzhEbgZDctLqHYkzY981YUaMUzcQyI8QTw0/6gotl5zjV8yHkTcn6"
				+ "u/3m9x+FkSFL00FGw7qHvaufn8xhA9U7BIhp63r1tYpMc+kcixz9hmJ2X/d8YRG6"
				+ "Rr7tsGwmLoncBHeIuQJBAMT+MwcVl73MHzNKpcTd0B+V/VMjTPp2ebGKLr1FR0xm"
				+ "YyVA6oxwnHHs8iMjDOeZGuZbczlxlYL0ZMOP++FU+dcCQQDED8vtZB4CjGVNXD1h"
				+ "dbctcxiSmQCfEoFQsp5/wKDsj+6Ex7rfa7Q0gEDwb6iBUAJY+iRvo9b/GPo91/t5"
				+ "b/2FAkAFv0TN2BttNh9S58KeU7pWsZGKc2UziXFTdKyYaQUDfZk25QXywvZNfJQz"
				+ "4jnmJ9lZDyH1cxLhly+5ZOH8WPZRAkBNm8snXxgZ/iUjaD3L1GL8FLFHZlU+on6Z"
				+ "yPrAqymIOrk5yi4IvoOIvLP3+NsExP0I54uAvaQfOm3kCRi8Sz5ZAkAGJPKhOHj2"
				+ "B5oYQfnqWtGz1M4T/tY8y/sfPionYXOrbksb6CDWQO1/PE6jogDICnDOcJlBRl9B" + "PuGSGeNNRva3";

		// 生成签名密钥
		byte[] keyBytes = Base64.getDecoder().decode(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setIssuer("marco").setSubject("token")
				.signWith(signatureAlgorithm, privateKey);

		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1L);
		userDTO.setUsername("joker");
		userDTO.setPassword("12333");
		// jwt中需要传递的内容
		builder.claim("Authorization", userDTO);
		builder.claim("InvalidTime", System.currentTimeMillis());
		return builder.compact();
	}

	/**
	 * 解密Jwt内容
	 *
	 * @param jwt
	 * @return
	 */
	public static Claims parseJwtRS256(String jwt)
	{
		Claims claims = null;

		try
		{
			// 读取公钥
			String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW3sbiqMiwkXx2QWeMPvdlej//"
					+ "ZYGVBNtLl/GDti/qzuzKk1tB4lV0algiBroDg7o41VFzZneq1doShGKjz0f1IJhO"
					+ "LX9eI4KVAMJeTdeTh21Or/YODyPhQQglfEoYPvyyK/DUa1VNSPI418k/sT3bfumd" + "rOb7qP7k/SBI/0hHswIDAQAB";
			// 生成签名公钥
			// byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
			byte[] keyBytes = Base64.getDecoder().decode(key);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);

			claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt).getBody();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return claims;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		try
		{
			// eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJtYXJjbyIsInN1YiI6InRva2VuIiwiaWQiOnsidXNlcklkIjoxLCJ1c2VybmFtZSI6Impva2VyIiwiZW1haWwiOm51bGwsIm9wZW5pZCI6bnVsbCwibW9iaWxlIjpudWxsLCJzdGF0dXMiOm51bGwsImNyZWF0ZURhdGUiOm51bGwsInVwZGF0ZURhdGUiOm51bGwsImxhc3RMb2dpbkRhdGUiOm51bGwsImxhc3RMb2dpbklwIjpudWxsLCJyb2xlSWQiOjEsInRhYmxlTnVtIjpudWxsLCJ0b2tlbiI6bnVsbCwiYWRtaW4iOmZhbHNlLCJhZG1pbk9yU3ViQWRtaW4iOmZhbHNlLCJpZGNhcmQiOm51bGx9fQ.dTOC9cYVNWylaH9y8wj9Z1Ks5XT8sueQAWWRPfRom93NjEBW3rmZZLif2s_Xi3TPTILEIFRPAq6kwDRE4BEp9-aoWM2YtPVW-VfcgpDHkTivHlcviZibmmU-8faQZKJu6sKwmDNAYOwcSymvK2Q7V4TPTKBoIQSj2fzdbSS7Zdc

			String res = buildJWTRS256();
			System.out.println(res);
			Claims claims = parseJwtRS256(res);
			System.out.println(claims);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public byte[] getPrivateKeyBytes()
	{
		return privateKeyBytes;
	}

	public void setPrivateKeyBytes(byte[] privateKeyBytes)
	{
		this.privateKeyBytes = privateKeyBytes;
	}

	public byte[] getPublicKeyBytes()
	{
		return publicKeyBytes;
	}

	public void setPublicKeyBytes(byte[] publicKeyBytes)
	{
		this.publicKeyBytes = publicKeyBytes;
	}

	public byte[] getSsoPrivateKeyBytes()
	{
		return ssoPrivateKeyBytes;
	}

	public void setSsoPrivateKeyBytes(byte[] ssoPrivateKeyBytes)
	{
		this.ssoPrivateKeyBytes = ssoPrivateKeyBytes;
	}

	public byte[] getSsoPublicKeyBytes()
	{
		return ssoPublicKeyBytes;
	}

	public void setSsoPublicKeyBytes(byte[] ssoPublicKeyBytes)
	{
		this.ssoPublicKeyBytes = ssoPublicKeyBytes;
	}

	public PrivateKey getSsoPrivateKey()
	{
		return ssoPrivateKey;
	}

	public void setSsoPrivateKey(PrivateKey ssoPrivateKey)
	{
		this.ssoPrivateKey = ssoPrivateKey;
	}

	public PublicKey getSsoPublicKey()
	{
		return ssoPublicKey;
	}

	public void setSsoPublicKey(PublicKey ssoPublicKey)
	{
		this.ssoPublicKey = ssoPublicKey;
	}

	public PrivateKey getAuthPrivateKey()
	{
		return authPrivateKey;
	}

	public void setAuthPrivateKey(PrivateKey authPrivateKey)
	{
		this.authPrivateKey = authPrivateKey;
	}

	public PublicKey getAuthPublicKey()
	{
		return authPublicKey;
	}

	public void setAuthPublicKey(PublicKey authPublicKey)
	{
		this.authPublicKey = authPublicKey;
	}

}
