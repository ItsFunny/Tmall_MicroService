/**
*
* @author joker 
* @date 创建时间：2018年6月13日 上午9:20:34
* 
*/
package com.tmall.server.product.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.filter.CharsetFilter;
import com.tmall.common.filter.RestBaseAuthFilter;
import com.tmall.common.interceptor.RestAuthTokenInterceptor;
import com.tmall.common.utils.JWTUtilFactoryBean;
import com.tmall.common.utils.KeyProperty;
import com.tmall.server.product.service.ICategoryService;
import com.tmall.server.product.service.impl.CategoryServiceImpl;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月13日 上午9:20:34
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperty.class, TmallConfigProperty.class, TmallProductProperty.class })
//@ComponentScan(basePackages =
//{ "com.tmall.server.product.service" })
//绑定的是db1下的sql文件
@MapperScan(basePackages =
{ "com.tmall.server.product.dao.db1" },sqlSessionFactoryRef="sqlSessionTwo")
public class ProductAPPServerConfiguraiton implements WebMvcConfigurer
{

	@Autowired
	private TmallProductProperty tmallProductProperty;

	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	@Autowired
	private KeyProperty keyProperty;

	@Bean
	public JWTUtilFactoryBean jwtUtilFactoryBean()
	{
		JWTUtilFactoryBean factoryBean = new JWTUtilFactoryBean(keyProperty.getAuthPrivateKey(),
				keyProperty.getAuthPublicKey(), keyProperty.getSsoPrivateKey(), keyProperty.getSsoPublicKey());
		return factoryBean;
	}

	@Bean(name = "charsetFilter")
	public FilterRegistrationBean<Filter> charsetFilterRegistration()
	{
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CharsetFilter());
		filterRegistrationBean.addInitParameter("encoding", "utf-8");
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}
	// @Bean
	// public RestJWTAuthFilter restJWTAuthFilter()
	// {
	//
	// return new RestJWTAuthFilter();
	// }
	// @Bean
	// public FilterRegistrationBean<Filter> filterRegistrationBean()
	// {
	// FilterRegistrationBean<Filter> filterRegistrationBean = new
	// FilterRegistrationBean<>();
	// filterRegistrationBean.setFilter(new RestJWTAuthFilter());
	// filterRegistrationBean.setUrlPatterns(Arrays.asList("/auth/*"));
	// filterRegistrationBean.setOrder(1);
	// return filterRegistrationBean;
	// }

	@Primary
	@Bean(name = "dataSourceOne")
	public DataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(tmallConfigProperty.getUsername());
		dataSource.setPassword(tmallConfigProperty.getPassword());
		dataSource.setUrl(tmallConfigProperty.getUrl());
		dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
		return dataSource;
	}
	
	@Primary
	@Bean(name = "sqlSessionOne")
	public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSourceOne") DataSource dataSource)
			throws IOException
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db0/*.xml"));
		return sqlSessionFactoryBean;
	}

	@Bean(name = "dataSourceTwo")
	public DataSource dataSourceTwo()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(tmallProductProperty.getAnotherUrl());
		dataSource.setUsername(tmallProductProperty.getAnotherUsername());
		dataSource.setPassword(tmallProductProperty.getAnotherPassword());
		dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
		return dataSource;
	}
	

	@Bean(name = "sqlSessionTwo")
	public SqlSessionFactoryBean sqlSessionFactoryBeanTwo(@Qualifier("dataSourceTwo") DataSource dataSource) throws IOException
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db1/*.xml"));
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
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
		freeMarkerConfigurer.setDefaultEncoding("utf-8");
		return freeMarkerConfigurer;
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqpEnabled", matchIfMissing = false)
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

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqpEnabled", matchIfMissing = false)
	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception
	{
		return new RabbitTemplate(connectionFactory());
	}

	@ConditionalOnProperty(prefix = "tmall.config", name = "amqpEnabled", matchIfMissing = false)
	@Bean
	public RabbitAdmin rabbitAdmin() throws Exception
	{
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		// need declare exchanger
		return rabbitAdmin;
	}

	//先暂时注释
//	@Bean
//	public RestAuthTokenInterceptor tokenInterceptor()
//	{
//		return new RestAuthTokenInterceptor();
//	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry)
//	{
//		WebMvcConfigurer.super.addInterceptors(registry);
//		registry.addInterceptor(tokenInterceptor()).addPathPatterns("/auth/**");
//	}
}
