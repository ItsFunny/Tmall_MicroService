package com.tmall.server.user;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.tmall.server.user", excludeFilters =
{ @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Mapper.class) })
@MapperScan(basePackages = "com.tmall.server.user.dao.db1", annotationClass = Mapper.class, sqlSessionFactoryRef = "db1SqlsessioFactory", sqlSessionTemplateRef = "db1SqlTemplate")
public class TmallUserServerApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TmallUserServerApplication.class).run(args);
	}
}
