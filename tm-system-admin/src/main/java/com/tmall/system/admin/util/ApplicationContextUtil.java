/**
*
* @author joker 
* @date 创建时间：2018年8月12日 下午8:06:14
* 
*/
package com.tmall.system.admin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月12日 下午8:06:14
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware
{
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		if (null != applicationContext)
		{
			ApplicationContextUtil.applicationContext = applicationContext;
		} else
		{
			throw new RuntimeException("ApplicationContext配置为空");
		}
	}
	public static ApplicationContext getApplicationContext()
	{
		return ApplicationContextUtil.applicationContext;
	}
}
