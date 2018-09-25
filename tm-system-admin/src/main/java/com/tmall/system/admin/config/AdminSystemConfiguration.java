/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午4:17:32
* 
*/
package com.tmall.system.admin.config;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.joker.library.service.IdWorkerService;
import com.joker.library.service.IdWorkerServiceTwitter;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.filter.CharsetFilter;
import com.tmall.common.interceptor.FeignBasicAuthRequestInterceptor;
import com.tmall.common.utils.JWTUtilFactoryBean;
import com.tmall.common.utils.KeyProperty;
import com.tmall.system.admin.shiro.TmallFreemarkerConfigurer;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月26日 下午4:17:32
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperty.class, TmallConfigProperty.class })
public class AdminSystemConfiguration implements WebMvcConfigurer
{
	@Autowired
	private KeyProperty keyProperty;
	@Autowired
	private TmallConfigProperty configProperty;
	
//	@Autowired
//	private TmallAdminConfigProperty tmallAdminConfigProperty;

	
	@Bean
	public IdWorkerService idWorkerService()
	{
		return new IdWorkerServiceTwitter(1L, 2L);
	}
	
	
	@Bean
	public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor()
	{
		return new FeignBasicAuthRequestInterceptor();
	}

	@Bean
	public JWTUtilFactoryBean jwtUtilFactoryBean()
	{
		JWTUtilFactoryBean factoryBean = new JWTUtilFactoryBean(keyProperty.getAuthPrivateKey(),
				keyProperty.getAuthPublicKey(), keyProperty.getSsoPrivateKey(), keyProperty.getSsoPublicKey());
		return factoryBean;
	}

	// @Bean
	// public TransactionManagementConfigurer TransactionManagementConfigurer()
	// {
	// DataSourceTransactionManager dataSourceTransactionManager=new
	// DataSourceTransactionManager();
	// dataSourceTransactionManager.setDataSource(dataSource(configProperty));
	// }
	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(configProperty.getUsername());
		dataSource.setPassword(configProperty.getPassword());
		dataSource.setUrl(configProperty.getUrl());
		dataSource.setDriverClassName(configProperty.getDriverClassName());
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean()
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		// sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		return sqlSessionFactoryBean;
	}

	@Bean
	public ViewResolver viewResolver()
	{
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer()
	{
		TmallFreemarkerConfigurer freeMarkerConfigurer = new TmallFreemarkerConfigurer();
		freeMarkerConfigurer.setDefaultEncoding("UTF-8");
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
//		freemarker.template.Configuration configuration=new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
//		configuration.setNumberFormat("0.##");
//		freeMarkerConfigurer.setConfiguration(configuration);
		return freeMarkerConfigurer;
	}

	@Bean
	public FilterRegistrationBean<Filter> systenFilterRegistrationBean()
	{
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CharsetFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addInitParameter("encoding", "utf-8");
		return filterRegistrationBean;
	}
	// @Override
	// public void addInterceptors(InterceptorRegistry registry)
	// {
	// registry.addInterceptor(new AuthInterceptor()).addPathPatterns("");
	// }

	@PostConstruct
	public void init()
	{
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter=new MappingJackson2HttpMessageConverter();
		ObjectMapper mapper=new ObjectMapper();
		SimpleModule simpleModule=new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
		mapper.registerModule(simpleModule);
		mappingJackson2HttpMessageConverter.setObjectMapper(mapper);
		converters.add(mappingJackson2HttpMessageConverter);
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}
}
