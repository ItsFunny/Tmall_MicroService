/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午9:28:11
* 
*/
package com.tmall.server.user.service.impl;


import com.tmall.server.user.common.model.TmallEmailLogin;
import com.tmall.server.user.common.model.TmallUser;
import com.tmall.server.user.service.AbstractLoginHandler;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月29日 下午9:28:11
*/
public class EmailLoginHandler extends AbstractLoginHandler
{

//	private EmailLoginHandler()
//	{
//		
//	}
	public EmailLoginHandler(String type)
	{
		super(type);
	}

	@Override
	protected TmallUser handler(FormUser formUser)
	{
		TmallEmailLogin emailLogin = loginService.loginByEmail(formUser.getLoginKey());
		Long userId = emailLogin.getUserId();
		TmallUser user = userService.findUserByUserId(userId);
//		UserDTO userDTO=new UserDTO();
//		user.to(userDTO);
		return user;
	}

}
