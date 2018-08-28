/**
*
* @author joker 
* @date 创建时间：2018年6月3日 下午10:06:56
* 
*/
package com.tmall.system.sso.config;

import java.util.Arrays;
import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.tmall.system.sso.filter.CharsetFilter;
import com.tmall.system.sso.filter.LoginFilter;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月3日 下午10:06:56
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperties.class, TmallConfigProperty.class })
//@MapperScan(basePackages= {""})
public class SSOSystemConfiguration
{

	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;
	
	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setUsername(tmallConfigProperty.getUsername());
		dataSource.setUrl(tmallConfigProperty.getUrl());
		dataSource.setPassword(tmallConfigProperty.getPassword());
		dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
		return dataSource;
	}
	@Bean
	public RestTemplate restTemplate()
	{
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	}
	@Bean
	public LoginFilter loginFilter()
	{
		return new LoginFilter();
	}
 	@Bean
	public FilterRegistrationBean<Filter>filterRegistration()
	{
		FilterRegistrationBean<Filter>filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(loginFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList(new String[] {"/login"}));
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}
 	@Bean
 	public FilterRegistrationBean<Filter>filterRegistrationBean()
 	{

 		FilterRegistrationBean<Filter> filterRegistrationBean=new FilterRegistrationBean<>();
 		filterRegistrationBean.addInitParameter("encoding", "utf-8");
 		filterRegistrationBean.setFilter(new CharsetFilter());
 		filterRegistrationBean.setOrder(1);
 		return filterRegistrationBean;
 	}
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean()
	{
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
//		sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		String[] strings=new String[]{"as"};
		return sqlSessionFactoryBean;
	}
	@Bean
	public ViewResolver viewResolver()
	{
		FreeMarkerViewResolver viewResolver=new FreeMarkerViewResolver();
		viewResolver.setSuffix(".html");
		Properties properties=new Properties();
		properties.setProperty("contentType", "text/html;charset=utf-8");
		viewResolver.setAttributes(properties);
		return viewResolver;
	}
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer()
	{
		FreeMarkerConfigurer freeMarkerConfigurer=new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
		freeMarkerConfigurer.setDefaultEncoding("utf-8");
		return freeMarkerConfigurer;
	}
	@Bean
	public ConnectionFactory connectionFactory() throws Exception
	{
		RabbitConnectionFactoryBean rabbitConnectionFactoryBean=new RabbitConnectionFactoryBean();
		rabbitConnectionFactoryBean.setHost("localhost");
		rabbitConnectionFactoryBean.setUsername("guest");
		rabbitConnectionFactoryBean.setPassword("guest");
		rabbitConnectionFactoryBean.afterPropertiesSet();
		CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory(rabbitConnectionFactoryBean.getObject());
		return cachingConnectionFactory;
	}
	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception
	{
		RabbitTemplate rabbitTemplate=new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		return rabbitTemplate;
	}
	@Bean
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin=new RabbitAdmin(connectionFactory());
		return rabbitAdmin;
	}
	
}
