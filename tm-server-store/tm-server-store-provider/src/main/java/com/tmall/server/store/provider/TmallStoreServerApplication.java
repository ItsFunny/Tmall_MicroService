package com.tmall.server.store.provider;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.tmall.server.spi")
@ComponentScan(basePackages = "com.tmall", excludeFilters =
{ @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Mapper.class) })
@MapperScan(basePackages = {"com.tmall"}, annotationClass = Mapper.class)
@EnableTransactionManagement
public class TmallStoreServerApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TmallStoreServerApplication.class).run(args);
	}
}
