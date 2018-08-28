/**
*
* @author joker 
* @date 创建时间：2018年8月19日 下午1:01:53
* 
*/
package com.tmall.system.sso.service;


import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月19日 下午1:01:53
*/
public interface IUserService
{
	ResultDTO<UserDTO> login(String json);
}
