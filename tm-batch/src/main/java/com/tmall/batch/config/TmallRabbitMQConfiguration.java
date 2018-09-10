/**
*
* @author joker 
* @date 创建时间：2018年9月3日 下午4:55:05
* 
*/
package com.tmall.batch.config;

import java.util.List;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joker.library.mq.consumer.abs.AbstractChannleAppeventConsumer;
import com.joker.library.mq.converters.JSONMessageConverter;
import com.tmall.batch.consumer.FacadedConsumer;
import com.tmall.batch.consumer.UserRecordListener;
import com.tmall.batch.mq.AbstractMQChannelConsumer;
import com.tmall.common.mq.TmallMQEnum;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 下午4:55:05
 */
@Configuration
@EnableConfigurationProperties(value =
{ TmallMQConfigProperty.class, })
public class TmallRabbitMQConfiguration
{
	@Autowired
	private TmallMQConfigProperty mqConfigProperty;

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setAddresses(mqConfigProperty.getAmqpAddrss());
		factory.setUsername(mqConfigProperty.getAmqpUsername());
		factory.setPassword(mqConfigProperty.getAmqpPassword());
		return factory;
	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled", matchIfMissing = false)
	public RabbitTemplate rabbitTemplate()
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
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
	public Queue recordQueue()
	{
		Queue queue = new Queue(TmallMQEnum.USER_RECORD.getQueueName());
		return queue;
	}

	@Bean
	@ConditionalOnProperty(prefix = "tmall.config.mq", name = "amqp-enabled")
	Binding recordBinding()
	{
		Queue queue = recordQueue();
		// return
		// BindingBuilder.bind(queue).to(recordExchange()).with(TmallMQEnum.USER_RECORD.getExchangeName().toUpperCase());
		return BindingBuilder.bind(queue).to(recordExchange())
				.with(TmallMQEnum.USER_RECORD.getRoutinKey().toUpperCase());
	}
	@Bean
	public UserRecordListener userRecordListener()
	{
		return new UserRecordListener();
	}
	
	@Bean
	public SimpleMessageListenerContainer container(List<AbstractChannleAppeventConsumer> consumers,List<Queue>queues)
	{
		SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setMessageConverter(new JSONMessageConverter());
		for (Queue queue : queues)
		{
			container.addQueueNames(queue.getName());
		}
		FacadedConsumer consumer=new FacadedConsumer(consumers);
		MessageListenerAdapter adapter=new MessageListenerAdapter(consumer, "onMessage");
		container.setMessageListener(adapter);
		adapter.setMessageConverter(container.getMessageConverter());
//		container.setMessageListener(consumer);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setDeclarationRetries(5);
		
		return container;
	}
}
