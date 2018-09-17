/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午5:14:38
* 
*/
package com.tmall.server.message.config;

import java.lang.annotation.Annotation;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.joker.library.mq.AppEventPublisher;
import com.joker.library.mq.AppEventRabbitMQPublish;
import com.joker.library.mq.LogEventPublisher;
import com.joker.library.mq.converters.JSONMessageConverter;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.server.message.holder.JobHolder;
import com.tmall.server.message.mq.TmallUserReturnCallBack;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午5:14:38
 */
@Configuration
@EnableConfigurationProperties(value =
{ TmallMQConfigProperty.class })
public class TmallMessageServerConfiguration
{
	@Autowired
	private TmallMQConfigProperty mqConfigProperty;

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public JobHolder jobHolder()
	{
		return JobHolder.getSingleTonJobHolder();
	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setHost(mqConfigProperty.getAmqpAddrss());
		cachingConnectionFactory.setPort(mqConfigProperty.getAmqpPort());
		cachingConnectionFactory.setUsername(mqConfigProperty.getAmqpUsername());
		cachingConnectionFactory.setPassword(mqConfigProperty.getAmqpPassword());
		cachingConnectionFactory.setPublisherConfirms(true);
		cachingConnectionFactory.setPublisherReturns(true);
		return cachingConnectionFactory;
	}

	// 规范的话这个最好还是选择起一个facaded confimCallback
	// @Bean
	// public ConfirmCallback facadedConfirmCallback()
	// {
	// TmallUserRecordConfirmCallback callback=new
	// TmallUserRecordConfirmCallback(TmallMQEnum.USER_RECORD.getRoutinKey());
	// callback.setNextCallback(null);
	// return callback;
	// }
	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public RabbitTemplate rabbitTemplate()
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		// rabbitTemplate.setConfirmCallback(facadedConfirmCallback());
		rabbitTemplate.setReturnCallback(new TmallUserReturnCallBack());
		rabbitTemplate.setMessageConverter(new JSONMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public RabbitAdmin rabbitAdmin()
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		rabbitAdmin.declareExchange(recordExchange());
		return rabbitAdmin;
	}

	@Bean(name = "recordExchange")
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public TopicExchange recordExchange()
	{
		TopicExchange exchange = new TopicExchange(TmallMQEnum.USER_RECORD.getExchangeName());
		return exchange;
	}

	@Bean(name = "recordQueue")
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public Queue recordQueue()
	{
		Queue queue = new Queue(TmallMQEnum.USER_RECORD.getQueueName());
		return queue;
	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	Binding recordBinding()
	{
		Queue queue = recordQueue();
		// return
		// BindingBuilder.bind(queue).to(recordExchange()).with(TmallMQEnum.USER_RECORD.getExchangeName().toUpperCase());
		return BindingBuilder.bind(queue).to(recordExchange())
				.with(TmallMQEnum.USER_RECORD.getRoutinKey().toUpperCase());
	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public AppEventPublisher eventPublisher()
	{
		if (mqConfigProperty.isAmqpEnabled())
		{
			return new AppEventRabbitMQPublish();
		} else
		{
			return new LogEventPublisher();
		}
	}
}
