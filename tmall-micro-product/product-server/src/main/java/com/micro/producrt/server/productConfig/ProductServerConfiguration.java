/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:34:59
* 
*/
package com.micro.producrt.server.productConfig;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午10:34:59
 */
@Configuration
// @Order(2)
@EnableConfigurationProperties(
{ TmallConfigProperty.class, KeyProperties.class })
public class ProductServerConfiguration implements InitializingBean
{
	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	@PostConstruct
	public void afterPropertiesSet()
	{
		System.out.println(tmallConfigProperty);
	}

	@Bean
	public DataSource dataSource(TmallConfigProperty tm)
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(tm.getUsername());
		dataSource.setPassword(tm.getPassword());
		dataSource.setDriverClassName(tm.getDriverClassName());
		dataSource.setUrl(tm.getUrl());
		return dataSource;
	}
	// @Bean
	// public DataSource dataSource()
	// {
	// DruidDataSource dataSource = new DruidDataSource();
	// dataSource.setUsername(tmallConfigProperty.getUsername());
	// dataSource.setPassword(tmallConfigProperty.getPassword());
	// dataSource.setDriverClassName(tmallConfigProperty.getDriverClassName());
	// dataSource.setUrl(tmallConfigProperty.getUrl());
	// return dataSource;
	// }
	// @Bean
	// public DataSource dataSource()
	// {
	// DruidDataSource dataSource = new DruidDataSource();
	// dataSource.setUsername("root");
	// dataSource.setPassword("123");
	// dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	// dataSource.setUrl("jdbc:mysql://localhost/cloud_tmall?characterEncoding=utf-8");
	// return dataSource;
	// }

	@Bean
	public QueryRunner queryRunner(TmallConfigProperty tm)
	{
		return new QueryRunner(dataSource(tm));
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(TmallConfigProperty tmallConfigProperty)
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource(tmallConfigProperty));
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		return sqlSessionFactoryBean;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer()
	{
		System.out.println(tmallConfigProperty);
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.micro.producrt.server.dao");
		return mapperScannerConfigurer;
	}

}
