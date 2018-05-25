/**
*
* @author joker 
* @date 创建时间：2018年5月17日 上午9:15:17
* 
*/
package com.micro.portal.config;

import java.util.EventListener;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.micro.portal.listener.TmallSessionListener;
import com.tmall.common.service.AbstractRedisService;
import com.tmall.common.service.INosqlService;
import com.tmall.common.service.impl.RedisServiceImpl;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 上午9:15:17
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperties.class, TmallConfigProperty.class })
@ComponentScan(basePackages =
{ "com.tmall.login.client.service" })
public class TmallPortalConfig implements InitializingBean
{
	private Logger logger = LoggerFactory.getLogger(TmallPortalConfig.class);
	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

//	@Bean
//	public DataSource dataSource()
//	{
//		DruidDataSource dataSource = new DruidDataSource();
//		dataSource.setUsername(tmallConfigProperty.getUsername());
//		dataSource.setPassword(tmallConfigProperty.getPassword());
//		dataSource.setUrl(tmallConfigProperty.getUrl());
//		dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
//		return dataSource;
//	}
//
//	@Bean
//	public QueryRunner queryRunner()
//	{
//		return new QueryRunner(dataSource());
//	}
//
//	@Bean
//	public SqlSessionFactoryBean sqlSessionFactoryBean()
//	{
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource());
//		// sqlSessionFactoryBean.setMapperLocations(mapperLocations);
//		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//		configuration.setMapUnderscoreToCamelCase(true);
//		return sqlSessionFactoryBean;
//	}
//
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer()
//	{
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer.setBasePackage("com.rebuildtmall.tmall_batch.dao");
//		return mapperScannerConfigurer;
//	}

	@Bean
	public RestTemplate restTemplate()
	{
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	public ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean()
	{
		ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(new TmallSessionListener());
		return servletListenerRegistrationBean;
	}

	// @Bean
	// public FilterRegistrationBean<Filter> checkLoginFilter(INosqlService
	// redisService,FeginLoginServerService serverService)
	// {
	// FilterRegistrationBean<Filter> filterRegistrationBean = new
	// FilterRegistrationBean<>();
	// filterRegistrationBean.setFilter(new
	// CheckUserLoginFilter(keyProperties,redisService,serverService));
	// filterRegistrationBean.setUrlPatterns(Arrays.asList("/order/test"));
	// return filterRegistrationBean;
	// }

	@Bean
	public JedisPool jedisPool()
	{
		return new JedisPool("localhost", 6379);
	}

	@Bean
	public INosqlService rediService()
	{
		AbstractRedisService redisService = new RedisServiceImpl();
		redisService.config(jedisPool());
		return redisService;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		keyProperties.init();
		logger.info(keyProperties.toString());
	}

}
