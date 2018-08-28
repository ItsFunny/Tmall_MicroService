package com.tmall.system.sso;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude =
{ RabbitAutoConfiguration.class })
@ComponentScan(basePackages =
{ "com.tmall" }, excludeFilters =
{ @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Mapper.class) })
@EnableFeignClients(basePackages="com.tmall.server.spi")
public class SSOSystemApp
{
	public static void main(String[] args)
	{
//		new SpringApplicationBuilder(SSOSystemApp.class).run(args);
		SpringApplication.run(SSOSystemApp.class, args);
	}
}
