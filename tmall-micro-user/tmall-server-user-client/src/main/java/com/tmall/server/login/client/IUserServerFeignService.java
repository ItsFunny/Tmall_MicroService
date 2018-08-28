/**
*
* @author joker 
* @date 创建时间：2018年6月14日 上午11:21:27
* 
*/
package com.tmall.server.login.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;

/**
 * server for other services ,dont register it to zuul
 * @author joker
 * @date 创建时间：2018年6月14日 上午11:21:27
 */
@FeignClient(name = "user", fallback = IUserServerFeignService.UserServerFallbackService.class)
public interface IUserServerFeignService
{
	
	/*
	 * 需要一个filter,证明是本人
	 */
	@PostMapping(value="/updateUser")
	ResultDTO<?>updateUser(@RequestBody UserDTO userDTO);
	
	
	@Component
	public static class UserServerFallbackService implements IUserServerFeignService
	{

		@Override
		public ResultDTO<?> updateUser(UserDTO userDTO)
		{
			return null;
		}

	}
}
