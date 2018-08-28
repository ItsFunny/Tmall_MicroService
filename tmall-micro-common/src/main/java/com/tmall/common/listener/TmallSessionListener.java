/**
*
* @author joker 
* @date 创建时间：2018年8月8日 下午10:29:35
* 
*/
package com.tmall.common.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.common.constants.RedisConstant;
import com.tmall.common.service.INosqlService;

/**
* 
* @author joker 
* @date 创建时间：2018年8月8日 下午10:29:35
*/
public class TmallSessionListener implements HttpSessionListener
{
	private Logger logger=LoggerFactory.getLogger(TmallSessionListener.class);
	@Autowired
	private INosqlService redisService;

	@Override
	public void sessionCreated(HttpSessionEvent se)
	{
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se)
	{
		try
		{
			HttpSession session = se.getSession();
			String id = session.getId();
			redisService.del(String.format(RedisConstant.USER_LOGIN_SYSTEMS, id));
		} catch (Exception e)
		{
			logger.error("redis服务器挂了",e);
			//发送邮箱
		}
	
	}

}
