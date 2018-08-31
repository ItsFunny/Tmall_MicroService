package com.tmall.system.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude=RabbitAutoConfiguration.class,scanBasePackages="com.tmall")
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.tmall.server.spi"})
//@ComponentScan(basePackages="com.tmall")
public class TmallAdminSystemApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TmallAdminSystemApplication.class, args);
	}
}
