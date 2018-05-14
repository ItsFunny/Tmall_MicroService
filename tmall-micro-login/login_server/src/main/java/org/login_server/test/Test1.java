/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午11:15:39
* 
*/
package org.login_server.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


/**
* 
* @author joker 
* @date 创建时间：2018年5月10日 上午11:15:39
*/
public class Test1
{
	public static Map<Integer, HttpSession> sessionMap=Collections.synchronizedMap(new HashMap<>());
	
	public static void put(Integer key,HttpSession session)
	{
		System.out.println("add session:"+session.getId());
		sessionMap.put(key, session);
	}
	
	public static HttpSession get(Integer key)
	{
		return sessionMap.get(key);
	}
	public static void ban(Integer key)
	{
		HttpSession session = sessionMap.get(key);
		System.out.println(session.getId()+"is removed");
		sessionMap.remove(key);
		session.invalidate();
	}
}
