/**
*
* @author joker 
* @date 创建时间：2018年5月18日 下午5:44:08
* 
*/
package org.login_server.config;

import static org.assertj.core.api.Assertions.in;

import java.beans.Introspector;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletContext;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月18日 下午5:44:08
 */
@ConfigurationProperties(prefix = "key")
public class KeyProperties
{
	private String loginPublicKeyPath;
	private String loginPrivateKeyPath;
	private String portalPublicKeyPath;
	private String portalPrivateKeyPath;

	private byte[] loginPublicKey;
	private byte[] loginPrivateKey;

	private byte[] portalPublicKey;
	private byte[] portalPrivateKey;
	

	public byte[] getPortalPublicKey()
	{
		if (null != portalPublicKey)
		{
			return portalPublicKey;
		}
		if (StringUtils.isEmpty(portalPublicKeyPath))
		{
			return null;
		}
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource(portalPublicKeyPath);
		InputStream inputStream = null;
		try
		{
			inputStream = resource.getInputStream();
			int index = 0;
			StringBuilder sb = new StringBuilder();
			while ((index = inputStream.read()) != -1)
			{
				sb.append((char) index);
			}
			this.portalPublicKey = Base64.getDecoder().decode(sb.toString());
		} catch (Exception e)
		{
			throwException("cannot load portal public key");
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
		return portalPublicKey;

	}
	public byte[] getPortalPrivat()
	{
		if (null != portalPublicKey)
		{
			return portalPublicKey;
		}
		if (StringUtils.isEmpty(portalPublicKeyPath))
		{
			return null;
		}
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource(portalPublicKeyPath);
		InputStream inputStream = null;
		try
		{
			inputStream = resource.getInputStream();
			int index = 0;
			StringBuilder sb = new StringBuilder();
			while ((index = inputStream.read()) != -1)
			{
				sb.append((char) index);
			}
			this.portalPublicKey = Base64.getDecoder().decode(sb.toString());
		} catch (Exception e)
		{
			throwException("cannot load portal public key");
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
		return portalPublicKey;

	}

	//这里可以在后台设置,然后让其自动添加某些方法
	public void init()
	{
		getLoginPublicKey();
		getLoginPrivateKey();
		getPortalPublicKey();
	}

	public byte[] getLoginPrivateKey()
	{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (null != loginPrivateKey)
		{
			return loginPrivateKey;
		}
		if (StringUtils.isEmpty(loginPrivateKeyPath))
		{
			return null;
		}
		// Path path = Paths.get(loginPublicKeyPath);
		Resource resource = resolver.getResource(loginPrivateKeyPath);
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
			loginPrivateKey=new String(builder).getBytes();
			System.out.println(new String(loginPrivateKey));
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
		return loginPrivateKey;
	}

	public byte[] getLoginPublicKey()
	{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (null != loginPublicKey)
		{
			return loginPublicKey;
		}
		if (StringUtils.isEmpty(loginPublicKeyPath))
		{
			return null;
		}
		// Path path = Paths.get(loginPublicKeyPath);
		Resource resource = resolver.getResource(loginPublicKeyPath);
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
			loginPublicKey=builder.toString().getBytes();
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
		return loginPublicKey;
	}

	public String getLoginPublicKeyPath()
	{
		return loginPublicKeyPath;
	}

	public void setLoginPublicKeyPath(String loginPublicKeyPath)
	{
		this.loginPublicKeyPath = loginPublicKeyPath;
	}

	public String getLoginPrivateKeyPath()
	{
		return loginPrivateKeyPath;
	}

	public void setLoginPrivateKeyPath(String loginPrivateKeyPath)
	{
		this.loginPrivateKeyPath = loginPrivateKeyPath;
	}

	private void throwException(String message)
	{
		throw new RuntimeException(message);
	}
	public String getPortalPublicKeyPath()
	{
		return portalPublicKeyPath;
	}
	public void setPortalPublicKeyPath(String portalPublicKeyPath)
	{
		this.portalPublicKeyPath = portalPublicKeyPath;
	}
	public String getPortalPrivateKeyPath()
	{
		return portalPrivateKeyPath;
	}
	public void setPortalPrivateKeyPath(String portalPrivateKeyPath)
	{
		this.portalPrivateKeyPath = portalPrivateKeyPath;
	}
	public byte[] getPortalPrivateKey()
	{
		return portalPrivateKey;
	}
	public void setPortalPrivateKey(byte[] portalPrivateKey)
	{
		this.portalPrivateKey = portalPrivateKey;
	}
	public void setLoginPublicKey(byte[] loginPublicKey)
	{
		this.loginPublicKey = loginPublicKey;
	}
	public void setLoginPrivateKey(byte[] loginPrivateKey)
	{
		this.loginPrivateKey = loginPrivateKey;
	}
	public void setPortalPublicKey(byte[] portalPublicKey)
	{
		this.portalPublicKey = portalPublicKey;
	}

	// public static void main(String[] args) throws IOException
	// {
	// //
	// System.out.println(KeyProperties.class.getClassLoader().getResource("keys/login_private.key").getPath());
	// // String url =
	// //
	// KeyProperties.class.getClassLoader().getResource("keys/login_private.key").getPath();
	// // Path path = Paths.get("keys/login_private.key");
	// //
	// // BufferedInputStream inputStream=new BufferedInputStream(new
	// // FileInputStream(new File(url)));
	// // StringBuilder sb=new StringBuilder();
	// // int index=0;
	// // while((index=inputStream.read())!=-1)
	// // {
	// // sb.append((char)index);
	// // }
	// // System.out.println(sb);
	// }

}
