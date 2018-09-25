/**
*
* @author joker 
* @date 创建时间：2018年9月4日 下午1:29:27
* 
*/
package com.joker.library.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 下午1:29:27
 */
public class AppEventRabbitMQPublish implements AppEventPublisher
{
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void publish(AppEvent event)
	{
		String uuid = event.getUuid();
		MicroCorrelationData correlationData=new MicroCorrelationData();
		correlationData.setId(uuid);
		correlationData.setType(event.getEventType());
		correlationData.setServerName(event.getServerName());
		correlationData.setCallBackData(event);
		rabbitTemplate.convertAndSend(event.getExchangeName(), event.getEventType().toUpperCase(), event,correlationData);
	}


}
