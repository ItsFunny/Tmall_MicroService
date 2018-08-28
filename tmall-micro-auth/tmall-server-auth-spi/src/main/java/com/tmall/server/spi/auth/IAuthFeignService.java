/**
*
* @author joker 
* @date 创建时间：2018年8月5日 下午10:27:59
* 
*/
package com.tmall.server.spi.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.utils.ResultUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月5日 下午10:27:59
 */
@FeignClient(name = "auth", fallback = IAuthFeignService.AuthFeignFallBackService.class)
public interface IAuthFeignService
{
	@RequestMapping(value = "/auth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String> auth(@RequestBody String token);

	@RequestMapping("/test")
	ResultDTO<String> test();

	@Component
	public static class AuthFeignFallBackService implements IAuthFeignService
	{
		@Override
		public ResultDTO<String> auth(String token)
		{
			return ResultUtils.fail("服务太过拥挤,请稍后再试");
		}

		@Override
		public ResultDTO<String> test()
		{
			return ResultUtils.fail("服务太过拥挤,请稍后再试");
		}
	}
}
