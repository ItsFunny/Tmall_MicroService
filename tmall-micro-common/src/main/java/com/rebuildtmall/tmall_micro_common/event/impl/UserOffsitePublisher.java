/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午10:31:17
* 
*/
package com.rebuildtmall.tmall_micro_common.event.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.rebuildtmall.tmall_micro_common.event.AppEvent;
import com.rebuildtmall.tmall_micro_common.event.AbstractAppPublish;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午10:31:17
 */
public class UserOffsitePublisher extends AbstractAppPublish
{
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void publish(AppEvent event)
	{
		rabbitTemplate.convertAndSend(this.getExchangeName(), this.getRoutingKey(), event);
	}

	@Override
	public void publish(String exchangeName, String routingKey, AppEvent event)
	{
		rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
	}

}
