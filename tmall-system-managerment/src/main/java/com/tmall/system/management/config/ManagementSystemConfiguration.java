/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午2:45:31
* 
*/
package com.tmall.system.management.config;


import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.tmall.common.constants.RabbitMQExchangeNameConstant;
import com.tmall.common.event.AppEventLogPublisher;
import com.tmall.common.event.AppEventPublisher;
import com.tmall.common.event.AppEventRabbitMQPublisher;
import com.tmall.system.management.filter.CharsetFilter;
import com.tmall.system.management.interceptor.UserInfoInterceptor;


/**
 * 
 * @author joker
 * @date 创建时间：2018年6月21日 下午2:45:31
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperty.class, TmallConfigProperty.class })
@MapperScan(basePackages= {
		"com.tmall.server.product.dao"
})
@ComponentScan(basePackages= {
		"com.tmall.gateway.client.service",
		"com.tmall.server.product.service"
})
public class ManagementSystemConfiguration implements WebMvcConfigurer
{
	@Autowired
	private KeyProperty keyProperty;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;
		
//	@Bean(name="multipartResolver")
//	public MultipartResolver multipartResolver()
//	{
//		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("utf-8");
//		resolver.setMaxUploadSize(1024000L);
//		return resolver;
//	}
	@Bean
	public FilterRegistrationBean<Filter>filterRegistration()
	{
		FilterRegistrationBean<Filter> filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CharsetFilter());
		filterRegistrationBean.addInitParameter("encoding", "utf-8");
		filterRegistrationBean.setOrder(0);
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
		return filterRegistrationBean;
	}
	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource=new DruidDataSource();
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
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
		org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean;
	}
	@Bean
	public ViewResolver viewResolver()
	{
		FreeMarkerViewResolver viewResolver=new FreeMarkerViewResolver();
		viewResolver.setSuffix(".html");
		Properties properties=new Properties();
		properties.setProperty("contentType", "text/html;charset=utf-8");
		return viewResolver;
	}
	@Bean
	public FreeMarkerConfigurer configurer()
	{
		FreeMarkerConfigurer configurer=new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/templates/");
		configurer.setDefaultEncoding("utf-8");
		return configurer;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(3135600);
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(3156000);
		registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/").setCachePeriod(3156000);
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(3156000);
		registry.addResourceHandler("/pics/**").addResourceLocations("classpath:/static/pics/");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new UserInfoInterceptor()).addPathPatterns("/**");
//		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqpEnabled",matchIfMissing=false)
	public ConnectionFactory connectionFactory() throws Exception
	{
		RabbitConnectionFactoryBean connectionFactoryBean=new RabbitConnectionFactoryBean();
		connectionFactoryBean.setHost(tmallConfigProperty.getAmqpHost());
		connectionFactoryBean.setUsername(tmallConfigProperty.getAmqpUsername());
		connectionFactoryBean.setPassword(tmallConfigProperty.getAmqpPassword());
		connectionFactoryBean.afterPropertiesSet();
		CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory(connectionFactoryBean.getObject());
		return cachingConnectionFactory;
	}
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqpEnabled",matchIfMissing=false)
	 public RabbitTemplate rabbitTemplate() throws Exception
	 {
		RabbitTemplate rabbitTemplate=new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
//		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	 }
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqpEnabled",matchIfMissing=false)
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin=new RabbitAdmin(connectionFactory());
		rabbitAdmin.declareExchange(userExchanger());
		return rabbitAdmin;
	}
	@Bean
	@ConditionalOnProperty(prefix="tmall.config",name="amqpEnabled",matchIfMissing=false)
	public TopicExchange userExchanger()
	{
		return new TopicExchange(RabbitMQExchangeNameConstant.USER);
	}
	
	@Bean
	public AppEventPublisher appEventPublisher() throws Exception
	{
		if(tmallConfigProperty.getAmqpEnabled())
		{
			AppEventRabbitMQPublisher rabbitMQPublisher=new AppEventRabbitMQPublisher();
			rabbitMQPublisher.setRabbitTemplate(rabbitTemplate());
			return rabbitMQPublisher;
		}else {
			return new AppEventLogPublisher();
		}
	}

}
