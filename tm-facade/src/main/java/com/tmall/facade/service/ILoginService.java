/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午9:59:11
* 
*/
package com.tmall.facade.service;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月28日 上午9:59:11
*/
public interface ILoginService
{
	 ResultDTO<UserDTO> login(String loginKey,String password,String storeAbbName);
	 
	 
	 ResultDTO<String>getAuth(String json);
	 
	 ResultDTO<String>loginAndAuth(String loginKey,String password,String storeAbbName);
}
