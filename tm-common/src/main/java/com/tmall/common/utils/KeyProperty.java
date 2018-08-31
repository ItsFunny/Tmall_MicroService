/**
*
* @author joker 
* @date 创建时间：2018年5月18日 下午5:44:08
* 
*/
package com.tmall.common.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月18日 下午5:44:08
 */
@ConfigurationProperties(prefix="tmall.key")
public class KeyProperty
{
	//auth-public-key-path
	
	private String authPublicKeyPath;
	private String authPrivateKeyPath;
	private String ssoPublicKeyPath;
	private String ssoPrivateKeyPath;
	
	
	private byte[] authPublicKey;
	private byte[] authPrivateKey;
	private byte[] ssoPublicKey;
	private byte[] ssoPrivateKey;
	
	
	public byte[] getSsoPrivateKey()
	{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (null != ssoPrivateKey)
		{
			return ssoPrivateKey;
		}
		if (StringUtils.isEmpty(ssoPrivateKeyPath))
		{
			return null;
		}
		Resource resource = resolver.getResource(ssoPrivateKeyPath);
		InputStream inputStream = null;
		try
		{
			inputStream = resource.getInputStream();
			int index = 0;
			StringBuilder builder = new StringBuilder();
			while ((index = inputStream.read()) != -1)
			{
				builder.append((char) index);
			}
			ssoPrivateKey=Base64.getDecoder().decode(builder.toString());
		} catch (IOException e)
		{
			throw new RuntimeException("cannot load the sso privte key");
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return ssoPrivateKey;
	}

	public byte[] getSsoPublicKey()
	{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (null != ssoPublicKey)
		{
			return ssoPublicKey;
		}
		if (StringUtils.isEmpty(ssoPublicKeyPath))
		{
			return null;
		}
		Resource resource = resolver.getResource(ssoPublicKeyPath);
		InputStream inputStream = null;
		try
		{
			inputStream = resource.getInputStream();
			int index = 0;
			StringBuilder builder = new StringBuilder();
			while ((index = inputStream.read()) != -1)
			{
				builder.append((char) index);
			}
			ssoPublicKey=Base64.getDecoder().decode(builder.toString());
		} catch (IOException e)
		{
			throw new RuntimeException("cannot load the sso public key");
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return ssoPublicKey;
	}


	//这里可以在后台设置,然后让其自动添加某些方法
	public void init()
	{
		getAuthPublicKey();
		getAuthPrivateKey();
	}
	

	public byte[] getAuthPrivateKey()
	{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (null != authPrivateKey)
		{
			return authPrivateKey;
		}
		if (StringUtils.isEmpty(authPrivateKeyPath))
		{
			return null;
		}
		// Path path = Paths.get(loginPublicKeyPath);
		Resource resource = resolver.getResource(authPrivateKeyPath);
		InputStream inputStream = null;
		try
		{
			// this.publicKey = Files.readAllBytes(path);
			inputStream = resource.getInputStream();
			int index = 0;
			StringBuilder builder = new StringBuilder();
			while ((index = inputStream.read()) != -1)
			{
				builder.append((char) index);
			}
//			loginPrivateKey = Base64.getDecoder().decode(new string);
			System.out.println(new String(builder));
//			loginPrivateKey=Base64.getDecoder().decode(new String(builder));
			authPrivateKey=Base64.getDecoder().decode(builder.toString());
		} catch (IOException e)
		{
			throw new RuntimeException("cannot load the login private key");
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return authPrivateKey;
	}

	public byte[] getAuthPublicKey()
	{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (null != authPublicKey)
		{
			return authPublicKey;
		}
		if (StringUtils.isEmpty(authPublicKeyPath))
		{
			return null;
		}
		// Path path = Paths.get(loginPublicKeyPath);
		Resource resource = resolver.getResource(authPublicKeyPath);
		InputStream inputStream = null;
		try
		{
			// this.publicKey = Files.readAllBytes(path);
			inputStream = resource.getInputStream();
			int index = 0;
			StringBuilder builder = new StringBuilder();
			while ((index = inputStream.read()) != -1)
			{
				builder.append((char) index);
			}
//			loginPublicKey = Base64.getDecoder().decode(builder.toString());
//			authPublicKey=builder.toString().getBytes();
			authPublicKey=Base64.getDecoder().decode(new String(builder));
		} catch (IOException e)
		{
			throw new RuntimeException("cannot load the login public key");
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return authPublicKey;
	}

	@Override
	public String toString()
	{
		return "KeyProperty [authPublicKeyPath=" + authPublicKeyPath + ", authPrivateKeyPath=" + authPrivateKeyPath
				+ ", authPublicKey=" + Arrays.toString(authPublicKey) + ", authPrivateKey="
				+ Arrays.toString(authPrivateKey) + "]";
	}

	public String getAuthPublicKeyPath()
	{
		return authPublicKeyPath;
	}

	public void setAuthPublicKeyPath(String authPublicKeyPath)
	{
		this.authPublicKeyPath = authPublicKeyPath;
	}

	public String getAuthPrivateKeyPath()
	{
		return authPrivateKeyPath;
	}

	public void setAuthPrivateKeyPath(String authPrivateKeyPath)
	{
		this.authPrivateKeyPath = authPrivateKeyPath;
	}

	public void setAuthPublicKey(byte[] authPublicKey)
	{
		this.authPublicKey = authPublicKey;
	}

	public void setAuthPrivateKey(byte[] authPrivateKey)
	{
		this.authPrivateKey = authPrivateKey;
	}

	public String getSsoPublicKeyPath()
	{
		return ssoPublicKeyPath;
	}

	public void setSsoPublicKeyPath(String ssoPublicKeyPath)
	{
		this.ssoPublicKeyPath = ssoPublicKeyPath;
	}

	public String getSsoPrivateKeyPath()
	{
		return ssoPrivateKeyPath;
	}

	public void setSsoPrivateKeyPath(String ssoPrivateKeyPath)
	{
		this.ssoPrivateKeyPath = ssoPrivateKeyPath;
	}

	public void setSsoPublicKey(byte[] ssoPublicKey)
	{
		this.ssoPublicKey = ssoPublicKey;
	}

	public void setSsoPrivateKey(byte[] ssoPrivateKey)
	{
		this.ssoPrivateKey = ssoPrivateKey;
	}



}
