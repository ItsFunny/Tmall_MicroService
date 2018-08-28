/**
*
* @author joker 
* @date 创建时间：2018年6月10日 下午10:11:57
* 
*/
package com.rebuildtmall.tmall_batch.amqp.consumer;

import static org.hamcrest.CoreMatchers.allOf;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.joker.library.mail.IEmailService;
import com.rebuildtmall.tmall_batch.amqp.AMQPListener;
import com.tmall.common.enums.RabbitMQEnum;
import com.tmall.common.event.AppEvent;

/**
* 
* @author joker 
* @date 创建时间：2018年6月10日 下午10:11:57
*/
public class EmailAMQPListener implements AMQPListener
{

	private Logger logger=LoggerFactory.getLogger(EmailAMQPListener.class);
	@Autowired
	private IEmailService emailService;
	
	@Override
	public String queueName()
	{
		return RabbitMQEnum.USER_ACCOUNT_CREATED.getQueueName();
	}

	@Override
	public String routingKeyValue()
	{
		return RabbitMQEnum.USER_ACCOUNT_CREATED.getRoutingKey();
	}

	@Override
	public void process(AppEvent event)
	{
		HashMap<String, String>data=event.getData();
		String to=null;
		String email = data.get("to");
		if(!StringUtils.isEmpty(email)&&email.indexOf("@")>0)
		{
			to=email;
		}
		if(StringUtils.isEmpty(to))
		{
			logger.warn("sorry,didnt find the email {}",event);
			return;
		}
		String content = data.get("content");
		try
		{
			emailService.sendMessage(to, "register", content);
		} catch (UnsupportedEncodingException | MessagingException e)
		{
			e.printStackTrace();
		}
		System.out.println(event);
	}

}
