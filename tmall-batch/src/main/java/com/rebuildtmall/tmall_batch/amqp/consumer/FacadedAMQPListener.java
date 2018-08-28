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

import com.rebuildtmall.tmall_batch.amqp.AMQPListener;
import com.tmall.common.event.AppEvent;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午10:49:58
 */
public class FacadedAMQPListener implements AMQPListener
{

	private Map<String, Collection<AMQPListener>> consumerMap = null;

	public FacadedAMQPListener()
	{
		this(Collections.emptyList());
	}

	public String[] queueNames(List<? extends AMQPListener> listeners)
	{
		List<String> list = listeners.stream().map(s -> s.queueName()).collect(Collectors.toList());
		return list.toArray(new String[listeners.size()]);
	}

	public FacadedAMQPListener(List<? extends AMQPListener> listeners)
	{
		if (CollectionUtils.isEmpty(listeners))
		{
			this.consumerMap = Collections.emptyMap();
			return;
		}
		Map<String, Collection<AMQPListener>> m = new HashMap<String, Collection<AMQPListener>>();
		for (AMQPListener AMQPListener : listeners)
		{
			System.out.println(AMQPListener.queueName());
			Collection<AMQPListener> c = m.get(AMQPListener.routingKeyValue());
			if (AMQPListener instanceof FacadedAMQPListener)
			{
				continue;
			}
			if (CollectionUtils.isEmpty(c))
			{
				c = new HashSet<>();
				m.put(AMQPListener.routingKeyValue(), c);
			}
			c.add(AMQPListener);
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
		Collection<AMQPListener> listeners = consumerMap.get(routingKey);
		if (CollectionUtils.isEmpty(listeners))
		{
			return;
		}
		for (AMQPListener AMQPListener : listeners)
		{
			AMQPListener.process(event);
		}

	}

	public Map<String, Collection<AMQPListener>> getConsumerMap()
	{
		return consumerMap;
	}

	public void setConsumerMap(Map<String, Collection<AMQPListener>> consumerMap)
	{
		this.consumerMap = consumerMap;
	}

	@Override
	public String routingKeyValue()
	{
		return null;
	}

}
