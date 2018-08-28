/**
*
* @author joker 
* @date 创建时间：2018年5月20日 下午3:22:46
* 
*/
package com.micro.portal.storage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
* 
* @author joker 
* @date 创建时间：2018年5月20日 下午3:22:46
*/
public enum SessionStorage
{
	SESSION_CONTAINER,
	;
	private Map<String, HttpSession>sessionStorage=new HashMap<String, HttpSession>();
	public void set(String token,HttpSession session)
	{
		sessionStorage.put(token, session);
	}
	public HttpSession get(String token)
	{
		if(sessionStorage.containsKey(token))
		{
			return sessionStorage.remove(token);
		}
		return null;
	}
	
}
