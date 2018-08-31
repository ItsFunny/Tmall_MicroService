/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午12:06:16
* 
*/
package com.tmall.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 
* @author joker 
* @date 创建时间：2018年6月21日 下午12:06:16
*/
public class AppEventLogPublisher implements AppEventPublisher
{	
	Logger logger=LoggerFactory.getLogger(AppEventLogPublisher.class);
	@Override
	public void publish(AppEvent event)
	{
		logger.info("event:{}",event);
	}


}
