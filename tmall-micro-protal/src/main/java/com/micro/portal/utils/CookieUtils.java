package com.micro.portal.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
* 
* @author joker 
* @date 创建时间：2018年5月14日 上午10:16:57
*/
public class CookieUtils
{
	public static String getUserToken(HttpServletRequest request,String key)
	{
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		Cookie[] cookies = request.getCookies();
		if(null==cookies || cookies.length<=0)
		{
			return null;
		}
		for (Cookie cookie : cookies)
		{
			if(cookie.getName().equals(key))
			{
				return cookie.getValue();
			}
		}
		return null;
	}
	public static void setUserToken(HttpServletResponse response,String key,String value,Integer expire)
	{
		Cookie cookie=new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(expire);
		response.addCookie(cookie);
	}
}
