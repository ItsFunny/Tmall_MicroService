/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午10:49:58
* 
*/
package com.rebuildtmall.tmall_batch.amqp.consumer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.rebuildtmall.tmall_batch.amqp.AmqpListener;

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

	public FacadedAmqpListener(List<? extends AmqpListener> listeners)
	{
		if (CollectionUtils.isEmpty(listeners))
		{
			this.consumerMap = Collections.emptyMap();
			return;
		}
		for (AmqpListener amqpListener : listeners)
		{
			Collection<AmqpListener> c = consumerMap.get(amqpListener.queueName());
			if (amqpListener instanceof FacadedAmqpListener)
			{
				continue;
			}
			if (CollectionUtils.isEmpty(c))
			{
				c = new HashSet<>();
				consumerMap.put(amqpListener.queueName(), c);
			}
			c.add(amqpListener);
		}
	}

	@Override
	public String queueName()
	{
		return null;
	}

	@Override
	public void process()
	{

	}

}
