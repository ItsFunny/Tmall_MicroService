/**
*
* @author joker 
* @date 创建时间：2018年8月19日 下午1:04:29
* 
*/
package com.tmall.server.spi.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tmall.common.config.FeignConfiguration;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.utils.ResultUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年8月19日 下午1:04:29
*/
@FeignClient(name="USER",configuration=FeignConfiguration.class)
public interface IUserFeignService
{
	@RequestMapping(value="/login",method=RequestMethod.POST)
	ResultDTO<UserDTO>login(@RequestBody String token);
	
//	@Component
	public static class UserFeignSericeFallback implements IUserFeignService
	{

		@Override
		public ResultDTO<UserDTO> login(String token)
		{
			return ResultUtils.fail("用户服务宕机了");
		}
		
	}
}
