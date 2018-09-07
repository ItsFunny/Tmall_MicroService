/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午8:44:05
* 
*/
package com.tmall.server.user.config;

import static org.assertj.core.api.Assertions.contentOf;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tmall.common.config.TmallConfigProperty;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午8:44:05
 */
@Configuration
@EnableConfigurationProperties(TmallConfigProperty.class)
public class TmallUserMQConfiguration
{
	@Autowired
	private TmallConfigProperty configProperty;
	
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqp-enabled",matchIfMissing=false)
	public ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory();
		cachingConnectionFactory.setPort(configProperty.getAmqpPort());
		cachingConnectionFactory.setHost(configProperty.getAmqpHost());
		cachingConnectionFactory.setUsername(configProperty.getAmqpUsername());
		cachingConnectionFactory.setPassword(configProperty.getAmqpPassword());
		cachingConnectionFactory.setPublisherConfirms(true);
		cachingConnectionFactory.setPublisherReturns(true);
		return cachingConnectionFactory;
	}
	
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqp-enabled",matchIfMissing=false)
	public RabbitTemplate rabbitTemplate()
	{
		RabbitTemplate rabbitTemplate=new RabbitTemplate();
		return rabbitTemplate;
	}


}
