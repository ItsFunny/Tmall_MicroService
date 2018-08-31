/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月31日 下午8:17:06
* 
*/
package com.tmall.common.utils;

import org.springframework.beans.factory.FactoryBean;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月31日 下午8:17:06
*/
public class JWTUtilFactoryBean implements FactoryBean<JWTUtils>
{
	
	private JWTUtils jwtUtils=new JWTUtils();
	private byte[] privateBytes;
	private byte[] publicBytes;
	
	private byte[] ssoPrivateBytes;
	private byte[] ssoPublicBytes;
	public JWTUtilFactoryBean(byte[] privateBytes, byte[] publicBytes,byte[] ssoPrivateBytes,byte[] ssoPublicBytes)
	{
		super();
		this.privateBytes = privateBytes;
		this.publicBytes = publicBytes;
		this.ssoPrivateBytes=privateBytes;
		this.ssoPublicBytes=publicBytes;
	}
	
	@Override
	public JWTUtils getObject() throws Exception
	{
		jwtUtils.setPrivateKeyBytes(this.privateBytes);
		jwtUtils.setPublicKeyBytes(this.publicBytes);
		jwtUtils.setSsoPrivateKeyBytes(this.ssoPrivateBytes);
		jwtUtils.setSsoPublicKeyBytes(this.ssoPublicBytes);
		jwtUtils.initKeys();
		return jwtUtils;
	}

	@Override
	public Class<?> getObjectType()
	{
		return JWTUtils.class;
	}

}
