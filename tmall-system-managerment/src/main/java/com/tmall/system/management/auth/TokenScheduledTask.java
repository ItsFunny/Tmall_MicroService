/**
*
* @Description
* @author joker 
* @date 创建时间：2018年6月28日 上午8:22:36
* 
*/
package com.tmall.system.management.auth;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.UserDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年6月28日 上午8:22:36
*/
@Component
public class TokenScheduledTask
{
	private Logger logger=LoggerFactory.getLogger(TokenScheduledTask.class);
	
	public static final Long refreshInterval= 60 * 1000 * 60 * 20L;
	
	public static String token="";
	
	private AuthTokenDTO authTokenDTO;
	
	
	public TokenScheduledTask()
	{
	}
	
	

	@Scheduled(fixedDelay= 60 * 1000 * 60 * 20L)
	public void refreshToken()
	{
		while(StringUtils.isBlank(token))
		{
			if(null==authTokenDTO)
			{
				throw new RuntimeException("init error,the authRequest should be detailed");
			}
			UserDTO userDTO=(UserDTO) SecurityUtils.getSubject().getPrincipal();
			if(null!=userDTO)
			{
				
			}
		}
	}


	public static String getToken()
	{
		return token;
	}


	public static void setToken(String token)
	{
		TokenScheduledTask.token = token;
	}
}
