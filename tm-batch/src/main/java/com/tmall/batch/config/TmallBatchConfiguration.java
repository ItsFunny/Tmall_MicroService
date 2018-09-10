/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午9:07:11
* 
*/
package com.tmall.batch.config;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 上午9:07:11
 */
@Configuration
@EnableConfigurationProperties(value =
{ TmallDBConfigProperty.class })
public class TmallBatchConfiguration
{
	@Autowired
	private TmallDBConfigProperty dbConfigProperty;

	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(dbConfigProperty.getUsername());
		dataSource.setPassword(dbConfigProperty.getPassword());
		dataSource.setDriverClassName(dbConfigProperty.getDriverClassName());
		dataSource.setUrl(dbConfigProperty.getUrl());
		return dataSource;
	}

	@Bean
	public QueryRunner queryRunner()
	{
		return new QueryRunner(dataSource());
	}

}
