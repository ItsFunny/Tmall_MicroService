/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午12:44:03
* 
*/
package com.tmall.facade.service;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年9月2日 下午12:44:03
*/
public interface IAuthService
{
	ResultDTO<UserDTO>getUserActions(String token);
}
