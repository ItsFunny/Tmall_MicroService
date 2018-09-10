package com.tmall.server.message;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude =
{ RabbitAutoConfiguration.class })
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.tmall.server.spi")
public class TmMessageApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TmMessageApplication.class).run(args);
	}
}
