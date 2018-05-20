package com.tmall.login.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="login")
public interface FeginLoginServerService
{
	/*
	 * token 校验
	 */
	@GetMapping("/authToken")
	String authToken(@RequestBody String token);
	
	
	@Component
	public static class LoginFallBackService implements FeginLoginServerService
	{
		@Override
		public String authToken(String token)
		{
			return null;
		}
	}
	
}
