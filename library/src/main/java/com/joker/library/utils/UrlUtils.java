/**
*
* @author joker 
* @date 创建时间：2018年6月22日 上午10:21:19
* 
*/
package com.joker.library.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月22日 上午10:21:19
 */
public class UrlUtils
{
	public static String getRedirectUrl(HttpServletRequest request)
	{
		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();

		if (null == queryString && !"".equals(queryString))
		{
			requestURL.append("?").append(queryString);
		}
		String t=requestURL.toString();
		return (t+=t.contains("?")?"&":"?");
	}
}
