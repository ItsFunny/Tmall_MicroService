package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableAutoConfiguration(exclude =
{ RabbitAutoConfiguration.class })
public class TmallSpringEurekaServerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(TmallSpringEurekaServerApplication.class, args);
	}
}
