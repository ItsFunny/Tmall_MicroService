/**
*
* @author joker 
* @date 创建时间：2018年9月4日 下午1:26:11
* 
*/
package com.joker.library.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 下午1:26:11
 */
public class LogEventPublisher implements AppEventPublisher
{
	private Logger logger = LoggerFactory.getLogger(LogEventPublisher.class);

	@Override
	public void publish(AppEvent event)
	{
		logger.info("[消息队列RabbitMQ]日志记录:{}", event);
	}

}
