/**
*
* @author joker 
* @date 创建时间：2018年6月9日 下午2:15:43
* 
*/
package com.tmall.system.sso.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
* 
* @author joker 
* @date 创建时间：2018年6月9日 下午2:15:43
*/
public class CookieUtils
{
	public static String getUserToken(HttpServletRequest request,String key)
	{
		Cookie[] cookies = request.getCookies();
		if(null==cookies||cookies.length==0)
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

}
