/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午10:26:58
* 
*/
package org.login_server.config;

import java.util.EventListener;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.login_server.listener.SessionListener;
import org.login_server.service.IdWorkerService;
import org.login_server.service.impl.IdWorkerServiceTwitter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.tmall.common.enums.RabbitMQEnum;
import com.tmall.common.impl.UserOffsitePublisher;
import com.tmall.common.service.AbstractRedisService;
import com.tmall.common.service.INosqlService;
import com.tmall.common.service.impl.RedisServiceImpl;

import redis.clients.jedis.JedisPool;

/**
* 
*/
@Configuration
@EnableConfigurationProperties(value =
{ TmallConfigProperty.class,KeyProperties.class })
public class TmallLoginConfig implements InitializingBean
{

	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	@Autowired
	private KeyProperties keyProperties;
	@Bean
	public JedisPool jedisPool()
	{
		JedisPool jedisPool = new JedisPool("localhost", 6379);
		return jedisPool;
	}

	@Bean
	@ConditionalOnBean(value =
	{ JedisPool.class })
	public INosqlService redisService()
	{
		AbstractRedisService redisService = new RedisServiceImpl();
		redisService.config(jedisPool());
		return redisService;
	}

	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(tmallConfigProperty.getUsername());
		dataSource.setPassword(tmallConfigProperty.getPassword());
		dataSource.setUrl(tmallConfigProperty.getUrl());
		dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
		return dataSource;
	}

	@Bean
	public QueryRunner queryRunner()
	{
		QueryRunner queryRunner = new QueryRunner(dataSource());
		return queryRunner;
	}

	@Bean
	public ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean()
	{
		ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(new SessionListener());
		return servletListenerRegistrationBean;
	}

	@Bean
	public IdWorkerService idWorkerService()
	{
		return new IdWorkerServiceTwitter(tmallConfigProperty.getWokerId(), tmallConfigProperty.getDataCenterId());
	}

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
		rabbitTemplate.setExchange(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getExchangeName());
		// rabbitTemplate.setMessageConverter(new JSONMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		rabbitAdmin.declareExchange(userAbnomalExchange());
		return rabbitAdmin;
	}

	@Bean
	public TopicExchange userAbnomalExchange()
	{
		return new TopicExchange(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getExchangeName());
	}

	@Bean
	public Queue userOffsiteQueue()
	{
		return new Queue(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getQueueName());
	}

	@Bean
	public Binding userOffsiteBinding()
	{
		return BindingBuilder.bind(userOffsiteQueue()).to(userAbnomalExchange())
				.with(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
	}

	@Bean
	public UserOffsitePublisher userOffsitePublisher()
	{
		UserOffsitePublisher userOffsitePublisher = new UserOffsitePublisher();
		userOffsitePublisher.setExchangeName(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getExchangeName());
		userOffsitePublisher.setRoutingKey(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
		return userOffsitePublisher;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		keyProperties.init();
	}
	
}
