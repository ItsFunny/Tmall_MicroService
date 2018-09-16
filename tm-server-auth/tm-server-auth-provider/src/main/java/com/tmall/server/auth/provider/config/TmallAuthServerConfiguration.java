package com.tmall.server.auth.provider.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.tmall.common.db.AbstractMySQLExtention;
import com.tmall.common.db.MySQLExtention;
import com.tmall.common.db.MySQLExtentionSupport;
import com.tmall.common.filter.CharsetFilter;
import com.tmall.common.utils.JWTUtilFactoryBean;
import com.tmall.common.utils.KeyProperty;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月3日 下午10:06:56
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperty.class, TmallConfigProperty.class })
public class TmallAuthServerConfiguration implements WebMvcConfigurer
{
	private Logger logger = LoggerFactory.getLogger(TmallAuthServerConfiguration.class);
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
	public MySQLExtention mySqlExtention()
	{
		AbstractMySQLExtention mySQLExtention = new MySQLExtentionSupport();
		mySQLExtention.config(tmallConfigProperty.getMysqlExtention());
		return mySQLExtention;
	}
//
//	@Bean
//	public AbstractLoginHandler loginHandler()
//	{
//		AbstractLoginHandler handler = new EmailLoginHandler(AbstractLoginHandler.EMAIL_TYPE);
//		handler.setNextHandler(new MobileLoginHandler(AbstractLoginHandler.MOBILE_TYPE));
//		return handler;
//	}

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

	// @Bean
	// public LoginFilter loginFilter()
	// {
	// return new LoginFilter();
	// }
	// @Bean
	// public FilterRegistrationBean<Filter>filterRegistration()
	// {
	// FilterRegistrationBean<Filter>filterRegistrationBean=new
	// FilterRegistrationBean<>();
	// filterRegistrationBean.setFilter(loginFilter());
	// filterRegistrationBean.setUrlPatterns(Arrays.asList(new String[]
	// {"/login"}));
	// filterRegistrationBean.setOrder(2);
	// return filterRegistrationBean;
	// }
	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean()
	{
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.addInitParameter("encoding", "utf-8");
		filterRegistrationBean.setFilter(new CharsetFilter());
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
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

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
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

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		return rabbitTemplate;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqp-enabled", matchIfMissing = false)
	@Bean
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		return rabbitAdmin;
	}

	@PostConstruct
	public void init()
	{
		keyProperty.getAuthPrivateKey();
		keyProperty.getAuthPublicKey();
		logger.info("{}", keyProperty);
	}

}
