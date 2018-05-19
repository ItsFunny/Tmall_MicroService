/**
*
* @author joker 
* @date 创建时间：2018年5月14日 下午9:14:36
* 
*/
package org.login_server.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 下午9:14:36
 */
public class LoginUtils
{
	// 获得客户端真实IP地址的方法二：
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getRedirectUrl(HttpServletRequest request)
	{
		StringBuffer buffer = request.getRequestURL();
		String queryString = request.getQueryString();
		if (!StringUtils.isEmpty(queryString))
		{
			buffer.append("?").append(queryString);
		}
		return buffer.toString();
	}
}
