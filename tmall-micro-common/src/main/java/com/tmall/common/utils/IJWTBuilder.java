/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月31日 下午7:54:52
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
* @date 创建时间：2018年7月31日 下午7:54:52
*/
public interface IJWTBuilder
{
	JWTUtils build() throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	void setPrivetKeyByte(byte[] privateKeyBytes);
	
	void setPublicKeyByte(byte[] publicKeyBytes);
}
