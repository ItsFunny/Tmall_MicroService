package com.tmall.server.gateway.provider;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.tmall.server.spi")
@EnableZuulProxy
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.tmall"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Mapper.class))
public class TmallGatewayServerApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TmallGatewayServerApplication.class).run(args);
	}
}
