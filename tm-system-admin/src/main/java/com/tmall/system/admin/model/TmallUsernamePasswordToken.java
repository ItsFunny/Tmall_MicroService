/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月28日 下午7:39:18
* 
*/
package com.tmall.system.admin.model;

import org.apache.shiro.authc.AuthenticationToken;

import com.tmall.common.dto.UserDTO;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月28日 下午7:39:18
 */
public class TmallUsernamePasswordToken implements AuthenticationToken
{

	// 用户主体
	private UserDTO user;
	// auth返回的token信息
	private String token;
	/**
	 * 
	 * @Description
	 * @author joker
	 * @date 创建时间：2018年7月28日 下午7:40:22
	 */
	private static final long serialVersionUID = 8988599301987867463L;

	public TmallUsernamePasswordToken()
	{
		super();
	}

	public TmallUsernamePasswordToken(UserDTO principal, String credentials)
	{
		this.user = principal;
		this.token = credentials;
	}

	@Override
	public Object getPrincipal()
	{
		return user;
	}

	@Override
	public Object getCredentials()
	{
		return token;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public UserDTO getUser()
	{
		return user;
	}

	public void setUser(UserDTO user)
	{
		this.user = user;
	}

}
