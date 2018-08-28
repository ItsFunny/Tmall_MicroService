/**
*
* @author joker 
* @date 创建时间：2018年8月28日 下午12:25:03
* 
*/
package com.tmall.serer.spi.gateway.feign.user.impl;

import org.springframework.stereotype.Component;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.serer.spi.gateway.feign.user.IGatewayUserFeignService;

import lombok.extern.slf4j.Slf4j;


/**
* 
* @author joker 
* @date 创建时间：2018年8月28日 下午12:25:03
*/
@Slf4j
//@Component
public class GatewayUserFeignFallback implements IGatewayUserFeignService
{

	@Override
	public ResultDTO<String> loginAndAuth(String loginKey, String password, String storeAbbName)
	{
		log.error("[loginAndAuth]用户登录触发了服务降级");
		return ResultUtils.fail("服务宕机了,请稍后再试");
	}
	
}
