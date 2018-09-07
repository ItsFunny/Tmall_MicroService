/**
*
* @author joker 
* @date 创建时间：2018年9月4日 下午3:38:33
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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 下午3:38:33
 */
@Configuration
@EnableConfigurationProperties(value =
{ TmallUserDBConfigProperty.class })
@MapperScan(basePackages = "com.tmall.server.user.dao.db2", sqlSessionFactoryRef = "db2SqlsesisonFactory", sqlSessionTemplateRef = "db2SqlTemplate")
public class TmallUserDB2Configuration
{
	@Autowired
	private TmallUserDBConfigProperty dbConfigProperty;

	@Bean(name = "db2")
	public DataSource db2DataSource()
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(dbConfigProperty.getDb2Username());
		dataSource.setPassword(dbConfigProperty.getDb2Password());
		dataSource.setUrl(dbConfigProperty.getDb2Url());
		dataSource.setDriverClassName(dbConfigProperty.getDb2DriverClassName());
		return dataSource;
	}

	@Bean(name = "db2SqlsesisonFactory")
	public SqlSessionFactory db2SqlSessionFacotry() throws Exception
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(db2DataSource());
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "db2SqlTemplate")
	public SqlSessionTemplate db2SqlSessionTemplate() throws Exception
	{
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(db2SqlSessionFacotry());
		return sqlSessionTemplate;
	}

}
