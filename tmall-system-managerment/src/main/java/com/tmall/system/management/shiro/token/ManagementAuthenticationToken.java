/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午6:59:58
* 
*/
package com.tmall.system.management.shiro.token;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

import com.tmall.common.dto.UserDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月21日 下午6:59:58
*/
public class ManagementAuthenticationToken  implements AuthenticationToken,Serializable
{

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年6月21日 下午7:01:04
	*/
	private static final long serialVersionUID = 1460182774345862166L;
	
	private UserDTO principal;
	private String credentials;
	
	@Override
	public Object getPrincipal()
	{
		return principal;
	}

	@Override
	public Object getCredentials()
	{
		return credentials;
	}

	public void setPrincipal(UserDTO principal)
	{
		this.principal = principal;
	}

	public void setCredentials(String credentials)
	{
		this.credentials = credentials;
	}
	
}
