/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午3:41:55
* 
*/
package com.micro.portal.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午3:41:55
 */
public class PortalUtils
{
	public static String getRedirectUrl(HttpServletRequest request)
	{
		StringBuffer buffer = request.getRequestURL();
		String queryString = request.getQueryString();
		if (!StringUtils.isEmpty(queryString))
		{
			buffer.append("?").append(queryString);
		}
		String t = buffer.toString();
		t += t.contains("?") ? "&" : "?";
		return t;
	}

	public static String getServerBaseUrl(HttpServletRequest request)
	{
		// http:localhost:8080/order/test  return http://localhost:8080
		StringBuffer url = request.getRequestURL();
		String serverUrl = url.substring(0, url.length() - request.getRequestURI().length());
		return serverUrl;
	}
}
