/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午10:03:39
* 
*/
package com.tmall.server.user.service.impl;

import com.tmall.server.user.common.model.TmallMobileLogin;
import com.tmall.server.user.common.model.TmallUser;
import com.tmall.server.user.service.AbstractLoginHandler;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月29日 下午10:03:39
*/
public class MobileLoginHandler extends AbstractLoginHandler
{

//	private MobileLoginHandler()
//	{
//		super();
//	}

	public MobileLoginHandler(String type)
	{
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected TmallUser handler(FormUser formUser)
	{
		TmallMobileLogin mobileLogin = loginService.loginByMobile(formUser.getLoginKey());
		Long userId = mobileLogin.getUserId();
		TmallUser user = userService.findUserByUserId(userId);
//		UserDTO userDTO=new UserDTO();
//		user.to(userDTO);
		return user;
	}

}
