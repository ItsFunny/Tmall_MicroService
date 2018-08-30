/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午4:17:32
* 
*/
package com.tmall.gateway.server.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.filter.CharsetFilter;
import com.tmall.common.interceptor.FeignBasicAuthRequestInterceptor;
import com.tmall.common.utils.JWTUtilFactoryBean;
import com.tmall.common.utils.KeyProperty;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月26日 下午4:17:32
 */
@Configuration
@Order(1)
@EnableConfigurationProperties(value =
{ KeyProperty.class, TmallConfigProperty.class })
// @MapperScan(basePackages= {
// ""
// })
public class TmallGatewayConfiguration implements WebMvcConfigurer
{
	@Autowired
	private KeyProperty keyProperty;
	@Autowired
	private TmallConfigProperty configProperty;

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
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

}
