/**
*
z

* 
* @author joker 
* @date 创建时间：2018年9月4日 上午11:21:11
* 
*/
package com.tmall.server.user.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 上午11:21:11
 */
@Configuration
@EnableConfigurationProperties(value =
{ TmallUserDBConfigProperty.class })
@MapperScan(basePackages="com.tmall.server.user.dao.db1",sqlSessionTemplateRef="db1SqlTemplate",sqlSessionFactoryRef="db1SqlsessioFactory")
public class tmallUserDB1Configuration
{
	@Autowired
	private TmallUserDBConfigProperty dbConfigProperty;
	@Bean(name = "db1")
	@Primary
	public DataSource db1DataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(dbConfigProperty.getDb1Username());
		dataSource.setPassword(dbConfigProperty.getDb1Passsword());
		dataSource.setUrl(dbConfigProperty.getDb1Url());
		dataSource.setDriverClassName(dbConfigProperty.getDb1DriverClassName());
		return dataSource;
	}
	
	@Bean(name = "db1SqlsessioFactory")
	@Primary
	public SqlSessionFactory db1SqlSessionFactoryBean() throws Exception
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(db1DataSource());
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	
	
	@Bean(name="db1SqlTemplate")
	@Primary
	public SqlSessionTemplate db1SqlSessionTemplate() throws Exception
	{
		SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(db1SqlSessionFactoryBean());
		return sqlSessionTemplate;
	}
	
}
