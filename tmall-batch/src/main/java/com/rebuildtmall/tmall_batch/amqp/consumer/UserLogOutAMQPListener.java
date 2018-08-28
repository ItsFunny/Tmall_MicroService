/**
*
* @author joker 
* @date 创建时间：2018年6月14日 上午9:36:17
* 
*/
package com.rebuildtmall.tmall_batch.amqp.consumer;

import org.springframework.beans.factory.annotation.Autowired;

import com.rebuildtmall.tmall_batch.amqp.AMQPListener;
import com.tmall.common.enums.RabbitMQEnum;
import com.tmall.common.event.AppEvent;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JsonUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年6月14日 上午9:36:17
*/
public class UserLogOutAMQPListener implements AMQPListener
{
	@Autowired
	private INosqlService redisService;

	@Override
	public String queueName()
	{
		return RabbitMQEnum.USER_LOGOUT.getQueueName();
	}

	@Override
	public String routingKeyValue()
	{
		return RabbitMQEnum.USER_LOGOUT.getRoutingKey();
	}

	@Override
	public void process(AppEvent event)
	{
		String json = redisService.get("");
		/*
		 * all serivices log out :invadilate session and this should be invoked into server 
		 */
		
	}

}
