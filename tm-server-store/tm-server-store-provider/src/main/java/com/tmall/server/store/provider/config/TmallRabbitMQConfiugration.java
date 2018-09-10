/**
*
* @author joker 
* @date 创建时间：2018年8月29日 上午11:08:51
* 
*/
package com.tmall.server.store.provider.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joker.library.mq.converters.JSONMessageConverter;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.enums.RabbitMQEnum;

/**
* 
* @author joker 
* @date 创建时间：2018年8月29日 上午11:08:51
*/
@Configuration
@EnableConfigurationProperties(TmallConfigProperty.class)
public class TmallRabbitMQConfiugration
{
	@Autowired
	private TmallConfigProperty tmallConfigProperty;
	
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqp-enabled",matchIfMissing=false)
	public ConnectionFactory connectionFactory() throws Exception
	{
		RabbitConnectionFactoryBean rabbitConnectionFactoryBean=new RabbitConnectionFactoryBean();
		rabbitConnectionFactoryBean.setUsername(tmallConfigProperty.getAmqpUsername());
		rabbitConnectionFactoryBean.setPassword(tmallConfigProperty.getAmqpPassword());
		rabbitConnectionFactoryBean.setHost(tmallConfigProperty.getAmqpHost());
		rabbitConnectionFactoryBean.afterPropertiesSet();
		CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory(rabbitConnectionFactoryBean.getObject());
		return cachingConnectionFactory;
	}
	
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqp-enabled",matchIfMissing=false)
	public RabbitTemplate rabbitTemplate()
	{

		RabbitTemplate rabbitTemplate=new RabbitTemplate();
		rabbitTemplate.setMessageConverter(new JSONMessageConverter());
		return rabbitTemplate;
	}
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqp-enabled",matchIfMissing=false)
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin=new RabbitAdmin(connectionFactory());
//		rabbitAdmin.declareExchange(exchange);
		return rabbitAdmin;
	}
	

}
