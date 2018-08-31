/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午9:20:42
* 
*/
package com.tmall.server.user.service;

import com.tmall.common.dto.UserDTO;
import com.tmall.server.user.service.AbstractLoginHandler.FormUser;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月29日 下午9:20:42
*/
public interface ILoginHandler
{

	UserDTO findUser(FormUser formUser);
}
