/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午10:49:58
* 
*/
package com.rebuildtmall.tmall_batch.amqp.consumer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.rebuildtmall.tmall_batch.amqp.AmqpListener;
import com.tmall.common.event.AppEvent;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午10:49:58
 */
public class FacadedAmqpListener implements AmqpListener
{

	private Map<String, Collection<AmqpListener>> consumerMap = null;

	public FacadedAmqpListener()
	{
		this(Collections.emptyList());
	}

	public String[] queueNames(List<? extends AmqpListener> listeners)
	{
		List<String> list = listeners.stream().map(s -> s.queueName()).collect(Collectors.toList());
		return list.toArray(new String[listeners.size()]);
	}

	public FacadedAmqpListener(List<? extends AmqpListener> listeners)
	{
		if (CollectionUtils.isEmpty(listeners))
		{
			this.consumerMap = Collections.emptyMap();
			return;
		}
		Map<String, Collection<AmqpListener>> m = new HashMap<String, Collection<AmqpListener>>();
		for (AmqpListener amqpListener : listeners)
		{
			System.out.println(amqpListener.queueName());
			Collection<AmqpListener> c = m.get(amqpListener.routingKeyValue());
			if (amqpListener instanceof FacadedAmqpListener)
			{
				continue;
			}
			if (CollectionUtils.isEmpty(c))
			{
				c = new HashSet<>();
				m.put(amqpListener.routingKeyValue(), c);
			}
			c.add(amqpListener);
		}
		this.consumerMap = m;
	}

	@Override
	public String queueName()
	{
		return "*";
	}

	@Override
	public void process(AppEvent event)
	{
		String routingKey = event.getRoutingKey();
		Collection<AmqpListener> listeners = consumerMap.get(routingKey);
		if (CollectionUtils.isEmpty(listeners))
		{
			return;
		}
		for (AmqpListener amqpListener : listeners)
		{
			amqpListener.process(event);
		}

	}

	public Map<String, Collection<AmqpListener>> getConsumerMap()
	{
		return consumerMap;
	}

	public void setConsumerMap(Map<String, Collection<AmqpListener>> consumerMap)
	{
		this.consumerMap = consumerMap;
	}

	@Override
	public String routingKeyValue()
	{
		return null;
	}

}
