/**
*
* @author joker 
* @date 创建时间：2018年9月7日 下午8:50:52
* 
*/
package com.tmall.batch.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joker.library.mq.AppEvent;
import com.joker.library.mq.consumer.abs.AbstractChannleAppeventConsumer;
import com.joker.library.utils.JsonUtil;
import com.rabbitmq.client.Channel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 专用于这个项目的,所以重写了message方法
 * @author joker
 * @date 创建时间：2018年9月7日 下午8:50:52
 */
@Slf4j
@Data
public abstract class AbstractMQChannelConsumer extends AbstractChannleAppeventConsumer
{

	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception
	{
		String className = getClass().getName();
		log.info("[{}],获取到消息:{}", className, message);
		try
		{
			String json = new String(message.getBody(), "utf-8");
			AppEvent event = JsonUtil.json2Object(json, AppEvent.class);
			doConsume(event,message.getMessageProperties().getDeliveryTag(), channel);
		} catch (Exception e)
		{
			log.error("[{}]消费消息出错,错误原因:{},重新放回队列中", className, e.getMessage());
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}

	}

}
