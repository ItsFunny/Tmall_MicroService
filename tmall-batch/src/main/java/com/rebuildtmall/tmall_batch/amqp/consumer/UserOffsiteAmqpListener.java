/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午8:43:11
* 
*/
package com.rebuildtmall.tmall_batch.amqp.consumer;

import com.rebuildtmall.tmall_batch.amqp.AmqpListener;
import com.rebuildtmall.tmall_micro_common.enums.RabbitMQEnum;

/**
* 
* @author joker 
* @date 创建时间：2018年5月15日 下午8:43:11
*/
public class UserOffsiteAmqpListener implements AmqpListener
{
	
	@Override
	public String queueName()
	{
		return RabbitMQEnum.USER_ABNORMAL_OFFSITE.getConsumeReturnQueueName();
	}

	@Override
	public void process()
	{
		
	}

}
