/**
*
* @author joker 
* @date 创建时间：2018年8月19日 下午1:03:36
* 
*/
package com.tmall.system.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.server.spi.user.IUserFeignService;
import com.tmall.system.sso.service.IUserService;

/**
* 
* @author joker 
* @date 创建时间：2018年8月19日 下午1:03:36
*/
@Service
public class UserServiceImpl implements IUserService
{
	
	@Autowired
	private IUserFeignService userFeignService;
	
	
	
	@Override
	public ResultDTO<UserDTO> login(String json)
	{
		return userFeignService.login(json);
	}

}
