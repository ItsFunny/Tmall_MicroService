/**
*
* @author joker 
* @date 创建时间：2018年8月31日 下午5:10:13
* 
*/
package com.tmall.server.spi.auth.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.TmallConfigTemplateDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.spi.auth.IAuthFeignService;

/**
* 
* @author joker 
* @date 创建时间：2018年8月31日 下午5:10:13
*/
@Component
public  class AuthFeignFallBackService implements IAuthFeignService
{
	@Override
	public ResultDTO<String> auth(String token)
	{
		return ResultUtils.fail("auth服务宕机了,服务太过拥挤,请稍后再试");
	}

	@Override
	public ResultDTO<String> test()
	{
		return ResultUtils.fail("auth服务宕机了,服务太过拥挤,请稍后再试");
	}

	@Override
	public ResultDTO<UserDTO> getUseActions(String token)
	{
		return ResultUtils.fail("auth服务宕机了,服务太过拥挤,请稍后再试");
	}

	@Override
	public ResultDTO<List<TmallConfigTemplateDTO>> getConfigTemplates(Map<String, Object> conditions)
	{
		return ResultUtils.fail("auth服务宕机了,服务太过拥挤,请稍后再试");
	}
}