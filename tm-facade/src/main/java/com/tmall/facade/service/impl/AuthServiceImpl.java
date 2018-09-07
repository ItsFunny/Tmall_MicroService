/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午12:44:32
* 
*/
package com.tmall.facade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.facade.service.IAuthService;
import com.tmall.server.spi.auth.IAuthFeignService;

/**
* 
* @author joker 
* @date 创建时间：2018年9月2日 下午12:44:32
*/
@Service
public class AuthServiceImpl implements IAuthService
{
	@Autowired
	private IAuthFeignService authFeignService;
	@Override
	public ResultDTO<UserDTO> getUserActions(String token)
	{
		return authFeignService.getUseActions(token);
	}

}
