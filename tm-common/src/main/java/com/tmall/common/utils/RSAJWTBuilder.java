/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月31日 下午7:58:41
* 
*/
package com.tmall.common.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月31日 下午7:58:41
*/
public class RSAJWTBuilder implements IJWTBuilder
{ 
	private JWTUtils jwtUtils=new JWTUtils();

	@Override
	public JWTUtils build() throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		this.jwtUtils.initKeys();
		return jwtUtils;
	}

	@Override
	public void setPrivetKeyByte(byte[] privateKeyBytes)
	{
		this.jwtUtils.setPrivateKeyBytes(privateKeyBytes);
	}

	@Override
	public void setPublicKeyByte(byte[] publicKeyBytes)
	{
		this.jwtUtils.setPublicKeyBytes(publicKeyBytes);
	}

}
