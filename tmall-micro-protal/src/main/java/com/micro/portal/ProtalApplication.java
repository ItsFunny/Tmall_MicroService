package com.micro.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude =
{ RabbitAutoConfiguration.class })
@EnableDiscoveryClient
@EnableFeignClients
public class ProtalApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ProtalApplication.class, args);
	}
}
