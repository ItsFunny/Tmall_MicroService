/**
*
* @author joker 
* @date 创建时间：2018年5月24日 下午2:08:44
* 
*/
package com.micro.portal.shiro.authentication;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月24日 下午2:08:44
 */
public class EncryptAuthenticationToken implements AuthenticationToken
{

	private Serializable data;
	private String encryptToken;
	private byte[] loginPublicKey;

	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年5月24日 下午2:09:20
	 */
	private static final long serialVersionUID = -4701999788348264789L;

	@Override
	public Object getPrincipal()
	{
		return this.data;
	}

	@Override
	public Object getCredentials()
	{
		return this.encryptToken;
	}

	public EncryptAuthenticationToken()
	{
		super();
	}

	public EncryptAuthenticationToken(Serializable data, String encryptToken)
	{
		super();
		this.data=data;
		this.encryptToken=encryptToken;
	}

	public String getEncryptToken()
	{
		return encryptToken;
	}

	public void setEncryptToken(String encryptToken)
	{
		this.encryptToken = encryptToken;
	}

	public byte[] getLoginPublicKey()
	{
		return loginPublicKey;
	}

	public void setLoginPublicKey(byte[] loginPublicKey)
	{
		this.loginPublicKey = loginPublicKey;
	}

	public Serializable getData()
	{
		return data;
	}

	public void setData(Serializable data)
	{
		this.data = data;
	}

}
