/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午10:26:58
* 
*/
package org.login_server.config;

import java.util.EventListener;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.login_server.listener.SessionListener;
import org.login_server.service.IdWorkerService;
import org.login_server.service.impl.IdWorkerServiceTwitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import redis.clients.jedis.JedisPool;

/**
* 
*/
@Configuration
@EnableConfigurationProperties(value =
{ TmallConfigProperty.class })
public class TmallLoginConfig
{

	@Autowired
	private TmallConfigProperty tmallConfigProperty;

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
		QueryRunner queryRunner = new QueryRunner(dataSource());
		return queryRunner;
	}

	@Bean
	public ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean()
	{
		ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(new SessionListener());
		return servletListenerRegistrationBean;
	}

	@Bean
	public JedisPool jedisPool()
	{
		JedisPool jedisPool = new JedisPool("localhost", 6379);
		return jedisPool;
	}

	@Bean
	public IdWorkerService idWorkerService()
	{
		return new IdWorkerServiceTwitter(tmallConfigProperty.getWokerId(), tmallConfigProperty.getDataCenterId());
	}
}
