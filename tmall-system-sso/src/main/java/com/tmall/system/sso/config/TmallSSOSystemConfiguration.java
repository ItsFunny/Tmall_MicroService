/**
*
* @author joker 
* @date 创建时间：2018年6月3日 下午10:06:56
* 
*/
package com.tmall.system.sso.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.db.AbstractMySQLExtention;
import com.tmall.common.db.MySQLExtention;
import com.tmall.common.db.MySQLExtentionSupport;
import com.tmall.common.filter.CharsetFilter;
import com.tmall.common.listener.TmallSessionListener;
import com.tmall.common.service.INosqlService;
import com.tmall.common.service.impl.RedisServiceImpl;
import com.tmall.common.utils.JWTUtilFactoryBean;
import com.tmall.common.utils.KeyProperty;
import com.tmall.system.sso.filter.SSOLoginFilter;

import redis.clients.jedis.JedisPool;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月3日 下午10:06:56
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperty.class, TmallConfigProperty.class })
@Order(2)
@MapperScan(basePackages =
{ "com.tmall" }, annotationClass = Mapper.class)
public class TmallSSOSystemConfiguration implements WebMvcConfigurer
{
	@Autowired
	private KeyProperty keyProperty;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	
	
	@Bean
	public INosqlService nosqlService()
	{
		RedisServiceImpl redisService=new RedisServiceImpl();
		JedisPool jedisPool=new JedisPool("localhost", 6379);
		redisService.config(jedisPool);
		
		return redisService;
		
	}
	
	@Bean
	public JWTUtilFactoryBean jwtUtilFactoryBean()
	{
		JWTUtilFactoryBean factoryBean = new JWTUtilFactoryBean(keyProperty.getAuthPrivateKey(),
				keyProperty.getAuthPublicKey(), keyProperty.getSsoPrivateKey(), keyProperty.getSsoPublicKey());
		return factoryBean;
	}

	@Bean
	public MySQLExtention mySqlExtention()
	{
		AbstractMySQLExtention mySQLExtention = new MySQLExtentionSupport();
		mySQLExtention.config(tmallConfigProperty.getMysqlExtention());
		return mySQLExtention;
	}

	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(tmallConfigProperty.getUsername());
		dataSource.setUrl(tmallConfigProperty.getUrl());
		dataSource.setPassword(tmallConfigProperty.getPassword());
		dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
		return dataSource;
	}

	@Bean
	public QueryRunner queryRunner()
	{
		return new QueryRunner(dataSource());
	}

	@Bean
	public RestTemplate restTemplate()
	{
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean()
	{

		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.addInitParameter("encoding", "utf-8");
		filterRegistrationBean.setFilter(new CharsetFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
	
	
	
	
	@Bean
	public FilterRegistrationBean<Filter> authFilterRegistration()
	{
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new SSOLoginFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/login"));
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<EventListener> sessionListener()
	{
		ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
		registrationBean.setListener(new TmallSessionListener());
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		return sqlSessionFactoryBean;
	}

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
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates/");
		freeMarkerConfigurer.setDefaultEncoding("utf-8");
		return freeMarkerConfigurer;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enable", matchIfMissing = false)
	@Bean
	public ConnectionFactory connectionFactory() throws Exception
	{
		RabbitConnectionFactoryBean rabbitConnectionFactoryBean = new RabbitConnectionFactoryBean();
		rabbitConnectionFactoryBean.setHost("localhost");
		rabbitConnectionFactoryBean.setUsername("guest");
		rabbitConnectionFactoryBean.setPassword("guest");
		rabbitConnectionFactoryBean.afterPropertiesSet();
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
				rabbitConnectionFactoryBean.getObject());
		return cachingConnectionFactory;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enable", matchIfMissing = false)
	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		return rabbitTemplate;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enable", matchIfMissing = false)
	@Bean
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		return rabbitAdmin;
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry)
//	{
//		registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");
//		WebMvcConfigurer.super.addInterceptors(registry);
//	}

}
