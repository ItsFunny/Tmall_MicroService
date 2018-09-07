/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午10:33:27
* 
*/
package com.tmall.system.admin.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joker.library.mq.AppEventPublisher;
import com.joker.library.mq.AppEventRabbitMQPublish;
import com.joker.library.mq.LogEventPublisher;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.mq.AbstractTmallConfirmCallback;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 上午10:33:27
 */
@Configuration
@EnableConfigurationProperties(value =
{ TmallAdminConfigProperty.class, TmallConfigProperty.class })
public class TmallAdminSystemMQConfiguration
{
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config", name = "amq-enabled", matchIfMissing = false)
	public ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setUsername(tmallConfigProperty.getAmqpUsername());
		cachingConnectionFactory.setPassword(tmallConfigProperty.getAmqpPassword());
		cachingConnectionFactory.setHost(tmallConfigProperty.getAmqpHost());
		cachingConnectionFactory.setPort(tmallConfigProperty.getAmqpPort());
		cachingConnectionFactory.setPublisherConfirms(true);
		cachingConnectionFactory.setPublisherReturns(true);
		return cachingConnectionFactory;
	}

//	@Bean
//	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
//	public ConfirmCallback facadedConfirmCallback()
//	{
//		TmallUserRecordConfirmCallback callback = new TmallUserRecordConfirmCallback(
//				AbstractTmallConfirmCallback.USER_RECORD_TYPE);
//		return callback;
//	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-ednabled", matchIfMissing = false)
	public RabbitTemplate rabbitTemplate()
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
//		rabbitTemplate.setConfirmCallback(facadedConfirmCallback());
		return rabbitTemplate;
	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
	public RabbitAdmin rabbitAdmin()
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		return rabbitAdmin;
	}

	@Bean
	public AppEventPublisher eventPublisher()
	{
		if (tmallConfigProperty.isEnabled())
		{
			return new AppEventRabbitMQPublish();
		} else
		{
			return new LogEventPublisher();
		}
	}

}
