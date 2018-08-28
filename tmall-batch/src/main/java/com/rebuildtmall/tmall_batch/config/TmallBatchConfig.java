/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午7:48:21
* 
*/
package com.rebuildtmall.tmall_batch.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.joker.library.mail.IEmailService;
import com.joker.library.mail.factory.DefaultEmailServiceFactory;
import com.joker.library.mail.property.EmailProperty;
import com.rabbitmq.client.AMQP.Basic.Return;
import com.rebuildtmall.tmall_batch.amqp.AMQPListener;
import com.rebuildtmall.tmall_batch.amqp.consumer.EmailAMQPListener;
import com.rebuildtmall.tmall_batch.amqp.consumer.FacadedAMQPListener;
import com.rebuildtmall.tmall_batch.amqp.consumer.UserOffsiteAMQPListener;
import com.sun.tools.classfile.StackMapTable_attribute.same_frame;
import com.tmall.common.constants.RabbitMQExchangeNameConstant;
import com.tmall.common.enums.RabbitMQEnum;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午7:48:21
 */

@EnableConfigurationProperties(value =
{ TmallConfigProperty.class })
@Configuration
@MapperScan(value= {"com.rebuildtmall.tmall_batch.dao"})
public class TmallBatchConfig
{
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	// @Bean
	// public DataSource dataSource()
	// {
	// DruidDataSource dataSource = new DruidDataSource();
	// dataSource.setUsername(tmallConfigProperty.getUsername());
	// dataSource.setPassword(tmallConfigProperty.getPassword());
	// dataSource.setUrl(tmallConfigProperty.getUrl());
	// dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
	// return dataSource;
	// }
	@Bean
	public IEmailService emailService()
	{
		DefaultEmailServiceFactory defaultEmailServiceFactory=new DefaultEmailServiceFactory();
		EmailProperty emailProperty=new EmailProperty();
		emailProperty.setAuth(true);
		emailProperty.setHost(tmallConfigProperty.getEmailHost());
		emailProperty.setProtocol("smtp");
		emailProperty.setSendEmailAccount(tmallConfigProperty.getEmailAccount());
		emailProperty.setSendEmailPwd(tmallConfigProperty.getEmailPwd());
		IEmailService emailService = defaultEmailServiceFactory.genereateEmailService(emailProperty);
		return emailService;
	}
	

	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("123");
		dataSource.setUrl("jdbc:mysql://localhost/cloud_tmall?characterEncoding=utf-8");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean()
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		return sqlSessionFactoryBean;
	}

//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer()
//	{
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		mapperScannerConfigurer.setBasePackage("dao");
//		return mapperScannerConfigurer;
//	}

	@Bean
	public QueryRunner queryRunner()
	{
		return new QueryRunner(dataSource());
	}

	@Bean
	public ConnectionFactory connectionFactory() throws Exception
	{
		RabbitConnectionFactoryBean connectionFactoryBean = new RabbitConnectionFactoryBean();
		connectionFactoryBean.setHost("localhost");
		connectionFactoryBean.afterPropertiesSet();
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
				connectionFactoryBean.getObject());
		return cachingConnectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
//		rabbitTemplate.setMessageConverter(new JSONMessageConverter());
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
		return new TopicExchange(RabbitMQExchangeNameConstant.USER);
	}

	@Bean
	Queue offSiteQueue()
	{
		Queue queue = new Queue(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getQueueName());
		return queue;
	}

	@Bean
	Binding offSiteBind()
	{
		return BindingBuilder.bind(offSiteQueue()).to(userExchange())
				.with(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
	}
	@Bean
	Queue emailQueue()
	{
		Queue queue=new Queue(RabbitMQEnum.USER_ACCOUNT_CREATED.getQueueName());
		return queue;
	}
	@Bean
	Binding emailBinding()
	{
		return BindingBuilder.bind(emailQueue()).to(userExchange()).with(RabbitMQEnum.USER_ACCOUNT_CREATED.getRoutingKey());
	}
	@Bean
	Queue logOutQueue()
	{
		Queue queue=new Queue(RabbitMQEnum.USER_LOGOUT.getQueueName());
		return queue;
	}
	@Bean
	Binding logoutBinding()
	{
		Queue queue=logOutQueue();
		return BindingBuilder.bind(queue).to(userExchange()).with(RabbitMQEnum.USER_LOGOUT.getRoutingKey());
	}
	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer(List<? extends AMQPListener> consumers)
			throws Exception
	{
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		FacadedAMQPListener AMQPListener = new FacadedAMQPListener(consumers);
		MessageListenerAdapter adapter = new MessageListenerAdapter(AMQPListener, "process");
//		adapter.setMessageConverter(new JSONMessageConverter());
		container.setQueueNames(AMQPListener.queueNames(consumers));
		container.setMessageListener(adapter);
//		container.setMessageConverter(new JSONMessageConverter());
		return container;
	}
//	@Bean
//	public SimpleMessageListenerContainer emailMessageContainer() throws Exception
//	{
//		SimpleMessageListenerContainer simpleMessageListenerContainer=new SimpleMessageListenerContainer();
//		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
//		simpleMessageListenerContainer.setQueues(emailQueue());
//		MessageListenerAdapter adapter=new MessageListenerAdapter(emailAMQPListener(),"process");
//		simpleMessageListenerContainer.setMessageListener(adapter);
//		return simpleMessageListenerContainer;
//	}
	
	@Bean
	public UserOffsiteAMQPListener userOffsiteAMQPListener()
	{
		UserOffsiteAMQPListener userOffsiteAMQPListener = new UserOffsiteAMQPListener();
		return userOffsiteAMQPListener;
	}
	@ConditionalOnBean(value= {IEmailService.class})
	@Bean
	public EmailAMQPListener emailAMQPListener()
	{
		return new EmailAMQPListener();
	}

//	@Bean
//	public UserOffsitePublisher userOffsitePublisher()
//	{
//		UserOffsitePublisher userOffsitePublisher = new UserOffsitePublisher();
//		userOffsitePublisher.setExchangeName(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getExchangeName());
//		userOffsitePublisher.setRoutingKey(RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
//		return userOffsitePublisher;
//	}

}
