/**
*
* @author joker 
* @date 创建时间：2018年5月17日 上午9:15:17
* 
*/
package com.micro.portal.config;

import java.util.Arrays;
import java.util.EventListener;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.micro.portal.filter.CheckUserLoginFilter;
import com.micro.portal.listener.TmallSessionListener;
import com.tmall.common.service.AbstractRedisService;
import com.tmall.common.service.INosqlService;
import com.tmall.common.service.impl.RedisServiceImpl;
import com.tmall.login.client.service.FeginLoginServerService;

import redis.clients.jedis.JedisPool;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 上午9:15:17
 */
@Configuration
@EnableConfigurationProperties(value= {
		KeyProperties.class
})
@ComponentScan(basePackages= {"com.tmall.login.client.service"})
public class TmallPortalConfig implements InitializingBean
{
	private Logger logger=LoggerFactory.getLogger(TmallPortalConfig.class);
	@Autowired
	private KeyProperties keyProperties;
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

	@Bean
	public FilterRegistrationBean<Filter> checkLoginFilter(INosqlService redisService,FeginLoginServerService serverService)
	{
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CheckUserLoginFilter(keyProperties,redisService,serverService));
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/order/test"));
		return filterRegistrationBean;
	}

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
