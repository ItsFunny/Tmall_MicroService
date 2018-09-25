/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午4:26:30
* 
*/
package com.joker.library.mq.consumer.abs;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.joker.library.mq.AppEvent;
import com.joker.library.utils.JsonUtil;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

/**
* 我的公共库中的,所有项目公用的打算
* @author joker 
* @date 创建时间：2018年9月8日 下午4:26:30
*/
@Slf4j
public abstract class AbstractChannleAppeventConsumer extends AbstractBaseConsumer implements ChannelAwareMessageListener
{

	@Override
	public void onMessage(Message message, Channel channel) throws Exception
	{
		String name=getClass().getName();
		log.info("[onMessage]获取到消息:{}",message);
		long startTime=System.currentTimeMillis();
		String json=new String(message.getBody(),"utf-8");
		AppEvent event = JsonUtil.json2Object(json,AppEvent.class);
		doConsume(event,message.getMessageProperties().getDeliveryTag(),channel);
		log.info("[{}]结束消费信息,耗时{}秒",name,(System.currentTimeMillis()-startTime)/1000.0);
	}
	public abstract void doConsume(AppEvent event,Long deliveryTag,Channel channel );
	
}
