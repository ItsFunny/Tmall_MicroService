/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午3:29:56
* 
*/
package com.tmall.server.product.server.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.utils.JWTUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年8月21日 下午3:29:56
*/
public class URLAuthCheckAOP
{
	@Autowired
	private JWTUtils jwtUtil;
	
	@Pointcut("@annotation(com.tmall.server.product.server.annotation.URLAuthCheck)")
	public void authUrl() {}
	
	
	
	@Before("authUrl()")
	public void authDetailUrl()
	{
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(null!=attributes)
		{
			HttpServletRequest request = attributes.getRequest();
			String uri = request.getRequestURI();
			//
			String token = request.getHeader(AuthConstant.AUTH_HEADER);
			AuthTokenDTO tokenDTO = jwtUtil.parseByAuthPublicKey(token);
			Long userId = tokenDTO.getData().getUserId();
			
		}
	}
}
