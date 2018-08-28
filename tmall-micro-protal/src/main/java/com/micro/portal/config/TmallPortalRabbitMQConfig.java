/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午8:06:53
* 
*/
package com.micro.portal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tmall.common.constants.RabbitMQExchangeNameConstant;
import com.tmall.common.enums.RabbitMQEnum;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午8:06:53
 */
@Configuration
public class TmallPortalRabbitMQConfig
{
	@Bean
	public ConnectionFactory connectionFactory() throws Exception
	{
		RabbitConnectionFactoryBean rabbitConnectionFactoryBean = new RabbitConnectionFactoryBean();
		rabbitConnectionFactoryBean.setHost("localhost");
		rabbitConnectionFactoryBean.afterPropertiesSet();
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
				rabbitConnectionFactoryBean.getObject());
		return cachingConnectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		return rabbitTemplate;
	}

	@Bean
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		rabbitAdmin.declareExchange(userExchange());
		return rabbitAdmin;
	}

	@Bean
	public TopicExchange userExchange()
	{
		return new TopicExchange(RabbitMQExchangeNameConstant.USER_STATUS_CHANGE);
	}

	@Bean
	public Queue offLineQueue()
	{
		return new Queue(RabbitMQEnum.USER_STATUS_CHANGE_BY_MANAGER.getQueueName());
	}

	@Bean
	public Binding offLineQueueBinding()
	{
		return BindingBuilder.bind(offLineQueue()).to(userExchange())
				.with(RabbitMQEnum.USER_STATUS_CHANGE_BY_MANAGER.getRoutingKey());
	}
	/*
	 * consumer未写
	 */
}
