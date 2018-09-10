/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午4:39:23
* 
*/
package com.tmall.batch.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.joker.library.mq.AppEvent;
import com.joker.library.mq.consumer.abs.AbstractChannleAppeventConsumer;
import com.rabbitmq.client.Channel;
import com.tmall.batch.mq.AbstractMQChannelConsumer;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月8日 下午4:39:23
 */
@Data
public class FacadedConsumer extends AbstractMQChannelConsumer
{
	//有2种方式存储,一种是采取这种方式
	//还有一种方式是采用map.key为eventType, value为Collection<AbstractChannelAppeventConsumer>集合
	private List<AbstractChannleAppeventConsumer> listeners;

	public FacadedConsumer(List<AbstractChannleAppeventConsumer> consumers)
	{
		if (null == consumers)
		{
			this.listeners = new ArrayList<AbstractChannleAppeventConsumer>();
		} else
		{
			this.listeners = consumers;
		}
	}

	@Override
	public void doConsume(AppEvent event, Long deliveryTag, Channel channel)
	{
		if (null == listeners)
		{
			return;
		}
		for (AbstractChannleAppeventConsumer abstractChannleAppeventConsumer : listeners)
		{
			if (abstractChannleAppeventConsumer == this || abstractChannleAppeventConsumer instanceof FacadedConsumer)
			{
				continue;
			}
			if (abstractChannleAppeventConsumer.getType().equals(event.getEventType()))
			{
				abstractChannleAppeventConsumer.doConsume(event, deliveryTag, channel);
			}
		}
	}

	@Override
	public String getType()
	{
		return "*";
	}

}
