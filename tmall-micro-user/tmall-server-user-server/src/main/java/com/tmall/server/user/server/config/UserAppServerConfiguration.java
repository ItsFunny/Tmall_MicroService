/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午9:03:31
* 
*/
package com.tmall.server.user.server.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.rabbitmq.client.ConnectionFactoryConfigurator;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.constants.RabbitMQExchangeNameConstant;
import com.tmall.common.db.MySQLExtentionSupport;
import com.tmall.common.db.factory.MySQLExtentionFactoryBean;
import com.tmall.common.enums.RabbitMQEnum;
import com.tmall.common.event.AppEventLogPublisher;
import com.tmall.common.event.AppEventPublisher;
import com.tmall.common.event.AppEventRabbitMQPublisher;
import com.tmall.common.service.AbstractRedisService;
import com.tmall.common.service.INosqlService;
import com.tmall.common.service.impl.RedisServiceImpl;
import com.tmall.common.utils.JWTUtilFactoryBean;
import com.tmall.common.utils.KeyProperty;
import com.tmall.server.user.service.AbstractLoginHandler;
import com.tmall.server.user.service.impl.EmailLoginHandler;
import com.tmall.server.user.service.impl.MobileLoginHandler;

import redis.clients.jedis.JedisPool;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月13日 下午9:03:31
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperty.class, TmallConfigProperty.class })
@ComponentScan(basePackages =
{ "com.tmall.server.user.service" })
@MapperScan(basePackages =
{ "com.tmall.server.user.dao" })
public class UserAppServerConfiguration
{
	@Autowired
	private KeyProperty keyProperty;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	@Bean
	public JWTUtilFactoryBean jwtUtilFactoryBean()
	{
		JWTUtilFactoryBean factoryBean = new JWTUtilFactoryBean(keyProperty.getAuthPrivateKey(),
				keyProperty.getAuthPublicKey(), keyProperty.getSsoPrivateKey(), keyProperty.getSsoPublicKey());
		return factoryBean;
	}

	@Bean
	public MySQLExtentionFactoryBean mySQLExtentionFactoryBean()
	{
		MySQLExtentionFactoryBean mySQLExtentionFactoryBean = new MySQLExtentionFactoryBean();
		MySQLExtentionSupport sqlExtentionSupport = new MySQLExtentionSupport();
		sqlExtentionSupport.config(tmallConfigProperty.getMysqlExtention());
		mySQLExtentionFactoryBean.setExtention(sqlExtentionSupport);
		return mySQLExtentionFactoryBean;
	}

	@Bean
	public AbstractLoginHandler loginHandler()
	{
		AbstractLoginHandler handler = new EmailLoginHandler(AbstractLoginHandler.EMAIL_TYPE);
		handler.setNextHandler(new MobileLoginHandler(AbstractLoginHandler.MOBILE_TYPE));
		return handler;
	}

	@Bean
	public INosqlService redisService()
	{
		AbstractRedisService redisService = new RedisServiceImpl();
		JedisPool jedisPool = new JedisPool("localhost", 6379);
		redisService.config(jedisPool);
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
		return new QueryRunner(dataSource());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
		return sqlSessionFactoryBean;
	}
	// @Bean
	// public MapperScannerConfigurer mapperScannerConfigurer()
	// {
	// MapperScannerConfigurer mapperScannerConfigurer=new
	// MapperScannerConfigurer();
	//// mapperScannerConfigurer.setBasePackage(basePackage);
	//// mapperScannerConfigurer.setSqlSessionFactoryBeanName("");
	// return mapperScannerConfigurer;
	// }

	@Bean
	public ViewResolver viewResolver()
	{
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setSuffix(".html");
		Properties properties = new Properties();
		properties.setProperty("contentType", "text/html;charset=utf-8");
		viewResolver.setAttributes(properties);
		return viewResolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer()
	{
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
		return freeMarkerConfigurer;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
	@Bean
	public ConnectionFactory connectionFactory() throws Exception
	{
		RabbitConnectionFactoryBean rabbitConnectionFactoryBean = new RabbitConnectionFactoryBean();
		rabbitConnectionFactoryBean.setHost(tmallConfigProperty.getAmqpHost());
		rabbitConnectionFactoryBean.setUsername(tmallConfigProperty.getAmqpUsername());
		rabbitConnectionFactoryBean.setPassword(tmallConfigProperty.getAmqpPassword());
		rabbitConnectionFactoryBean.afterPropertiesSet();
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
				rabbitConnectionFactoryBean.getObject());
		return cachingConnectionFactory;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		// rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
	@Bean
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		// declare queue
		rabbitAdmin.declareExchange(userExchange());
		return rabbitAdmin;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
	@Bean
	public TopicExchange userExchange()
	{
		return new TopicExchange(RabbitMQExchangeNameConstant.USER);
	}

	@Bean
	public AppEventPublisher appEventPublisher()
	{
		if (tmallConfigProperty.getAmqpEnabled())
		{
			return new AppEventRabbitMQPublisher();
		} else
		{
			return new AppEventLogPublisher();
		}
	}

}
