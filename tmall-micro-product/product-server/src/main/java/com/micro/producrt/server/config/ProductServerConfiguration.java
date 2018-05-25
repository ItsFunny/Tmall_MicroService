/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:34:59
* 
*/
package com.micro.producrt.server.config;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午10:34:59
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperties.class, TmallConfigProperty.class })
public class ProductServerConfiguration
{
	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	@Bean
	public DataSource dataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(tmallConfigProperty.getUsername());
		dataSource.setPassword(tmallConfigProperty.getPassword());
		dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
		dataSource.setUrl(tmallConfigProperty.getUrl());
		return dataSource;
	}
	@Bean
	public QueryRunner queryRunner()
	{
		return new QueryRunner(dataSource());
	}
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean()
	{
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		return sqlSessionFactoryBean;
	}
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer()
	{
		MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("");
		return mapperScannerConfigurer;
	}
	
}
