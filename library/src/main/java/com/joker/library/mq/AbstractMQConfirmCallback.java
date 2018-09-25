/**
*
* @author joker 
* @date 创建时间：2018年9月5日 上午9:32:16
* 
*/
package com.joker.library.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 上午9:32:16
*/
@Data
public abstract class AbstractMQConfirmCallback<T> implements ConfirmCallback
{
	private Class<T> type;
	
	public AbstractMQConfirmCallback(Class<T> type)
	{
		this.type=type;
	}
	
	
	
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause)
	{
		if(ack)
		{
			//发送成功
			doSuccess(correlationData);
		}else {
			//发送失败
			doFail(correlationData, cause);
		}
	}
	
	public abstract void doSuccess(CorrelationData correlationData);
	
	public abstract void doFail(CorrelationData correlationData,String cause);
	
	
}
