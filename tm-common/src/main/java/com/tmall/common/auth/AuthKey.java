/**
*
* @Description
* @author joker 
* @date 创建时间：2018年6月27日 下午8:28:18
* 
*/
package com.tmall.common.auth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 
 * @When when ever u want
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年6月27日 下午8:28:18
 */
public class AuthKey implements InitializingBean
{
	private String authPrivateKeyPath;
	private byte[] authPrivateKey;
	private String authPublicKeyPath;
	private byte[] authPublicKey;

	private AuthKey()
	{

	}

	private static AuthKey authKey = null;

	public static AuthKey getAuthKey() throws Exception
	{
		if (null == authKey)
		{
			synchronized (AuthKey.class)
			{
				if (null == authKey)
				{
					authKey = new AuthKey();
					authKey.afterPropertiesSet();
					return authKey;
				}
			}
		}
		return authKey;
	}

	public String getAuthPrivateKeyPath()
	{
		return authPrivateKeyPath;
	}

	public void setAuthPrivateKeyPath(String authPrivateKeyPath)
	{
		this.authPrivateKeyPath = authPrivateKeyPath;
	}

	public byte[] getAuthPrivateKey()
	{
		return authPrivateKey;
	}

	public void setAuthPrivateKey(byte[] authPrivateKey)
	{
		this.authPrivateKey = authPrivateKey;
	}

	public String getAuthPublicKeyPath()
	{
		return authPublicKeyPath;
	}

	public void setAuthPublicKeyPath(String authPublicKeyPath)
	{
		this.authPublicKeyPath = authPublicKeyPath;
	}

	public byte[] getAuthPublicKey()
	{
		return authPublicKey;
	}

	public void setAuthPublicKey(byte[] authPublicKey)
	{
		this.authPublicKey = authPublicKey;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		this.authPrivateKey = getBytes("classpath:keys/AuthPrivateKey.key");
		this.authPublicKey = getBytes("classpath:keys/AuthPublicKey.key");

	}

	private byte[] getBytes(String path) throws IOException
	{
		Resource resource = new PathMatchingResourcePatternResolver().getResource(path);
		byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
		return bytes;
	}

}
