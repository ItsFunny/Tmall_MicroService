/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午12:05:43
* 
*/
package com.tmall.common.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月21日 下午12:05:43
 */
public class AppEventRabbitMQPublisher implements AppEventPublisher
{
	private RabbitTemplate rabbitTemplate;
	

	
	@Override
	public void publish(AppEvent event)
	{
		rabbitTemplate.convertAndSend(event.getExchangeName(), event.getRoutingKey(), event);
	}



	public RabbitTemplate getRabbitTemplate()
	{
		return rabbitTemplate;
	}


	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate)
	{
		this.rabbitTemplate = rabbitTemplate;
	}

}
