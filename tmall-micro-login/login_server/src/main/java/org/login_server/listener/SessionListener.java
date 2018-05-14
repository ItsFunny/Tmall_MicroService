/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午11:21:55
* 
*/
package org.login_server.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
* 
* @author joker 
* @date 创建时间：2018年5月10日 上午11:21:55
*/
@WebListener
public class SessionListener implements HttpSessionListener
{

	@Override
	public void sessionCreated(HttpSessionEvent arg0)
	{
		System.out.println("create session");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{
		String id = event.getSession().getId();
		System.out.println(id+"is invalidated");
	}

}
