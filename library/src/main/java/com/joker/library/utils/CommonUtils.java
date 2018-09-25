/**
*
* @author joker 
* @date 创建时间：2018年2月15日 上午10:30:54
* 
*/
package com.joker.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author joker
 * @date 创建时间：2018年2月15日 上午10:30:54
 */
public class CommonUtils
{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public static String getRedirectUrl()
	{
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return getRedirectUrlString(request);
	}
	/**
	 * 用于创建被中断时恢复后返回的url
	 * 
	 * @param request
	 * @return
	 * @author joker
	 * @date 创建时间：2018年2月15日 上午10:48:56
	 */
	public static String getRedirectUrlString(HttpServletRequest request)
	{
		// String redirect = null;
		// StringBuffer requestURL = request.getRequestURL();
		// String queryString = request.getQueryString();
		// if (null != queryString && !"".equals(queryString))
		// {
		// redirect = new
		// String(requestURL.append("?").append(queryString).append("&"));
		// } else
		// {
		// redirect = new String(requestURL.append("?"));
		// }
		// return redirect;
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
		// http:localhost:8080/order/test return http://localhost:8080
		StringBuffer url = request.getRequestURL();
		String serverUrl = url.substring(0, url.length() - request.getRequestURI().length());
		return serverUrl;
	}

	/**
	 * 利用反射将java的model对象转为sql中的表名字 如:model:CategoryPicture 则sql中的为category_picture
	 * 
	 * @param javaModelName
	 * @return
	 * @author joker
	 * @date 创建时间：2018年1月27日 下午5:09:29
	 */
	public static String getSqlModel(String javaModelName)
	{
		char[] arr = javaModelName.toCharArray();
		int more = 0;
		String result = null;
		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] > 65 && arr[i] < 97)
			{
				more++;
			}
		}
		if (more != 0)
		{
			char[] newArr = new char[arr.length + more];
			for (int i = 0, j = 0; i < arr.length; i++, j++)
			{
				char temp = ' ';
				if (arr[i] > 65 && arr[i] < 97 && i > 0)
				{
					temp = arr[i];
				}
				if (temp != ' ')
				{
					newArr[j] = '_';
					newArr[j + 1] = temp;
					j++;
				} else
				{
					newArr[j] = arr[i];
				}
			}
			result = new String(newArr).toLowerCase();
		} else
		{
			result = javaModelName.toLowerCase();
		}
		return result;
	}
	/*
	 * 检验是否有中文,简单方式
	 */
	public static Boolean isContainsChinese(String str)
	{
		char[] array = str.toCharArray();
		for (char c : array)
		{
			if( c >= 0x4E00 &&  c <= 0x9FA5)
			{
				return true;
			}
		}
		return false;
	}

	// 获得客户端真实IP地址的方法一：
	public static String getRemortIP(HttpServletRequest request)
	{
		if (request.getHeader("x-forwarded-for") == null)
		{
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

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
		String res = ip.replaceAll(":", ".");
		return res;
	}

	public static String simpleDate2String(Date date)
	{
		return dateFormat.format(date);
	}

//	public static void main(String[] args)
//	{
//		String string = simpleDate2String(new Date());
//		System.out.println(string);
//	}

	public static Date string2Date(String dateStr)
	{
		try
		{
			return dateFormat.parse(dateStr);
		} catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 校验输入的数据是否合法
	 * 
	 * @param str
	 * @return
	 * @author joker
	 * @date 创建时间：2018年6月13日 下午6:26:40
	 */
	public static String validStringException(String str)
	{
		if (!validString(str))
		{
			throw new RuntimeException(escapeHtml(str) + "数据不合法");
		}
		return str;
	}

	public static boolean validString(String str)
	{
		// if (StringUtils.isBlank(str))
		// {
		// return true;
		// }
		if (StringUtils.indexOfAny(str, "&<>\"'") >= 0)
		{
			return false;
		} else
		{
			return true;
		}
	}

	//
	public static String escapeHtml(String s)
	{
		if (StringUtils.isBlank(s))
		{
			return s;
		}
		if (StringUtils.indexOf(s, "&") >= 0)
		{
			s = StringUtils.replace(s, "&", "&amp;");
		}
		if (StringUtils.indexOf(s, "<") >= 0)
		{
			s = StringUtils.replace(s, "<", "&lt;");
		}
		if (StringUtils.indexOf(s, ">") >= 0)
		{
			s = StringUtils.replace(s, ">", "&gt;");
		}
		if (StringUtils.indexOf(s, "\"") >= 0)
		{
			s = StringUtils.replace(s, "\"", "&quot;");
		}
		if (StringUtils.indexOf(s, "'") >= 0)
		{
			s = StringUtils.replace(s, "'", "&#039;");
		}
		return s;
	}

}
