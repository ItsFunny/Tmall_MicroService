/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午9:12:51
* 
*/
package com.tmall.server.message.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 下午9:12:51
*/
public class TmallUserReturnCallBack implements ReturnCallback
{

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey)
	{
		System.out.println("--------------------");
	}

}
