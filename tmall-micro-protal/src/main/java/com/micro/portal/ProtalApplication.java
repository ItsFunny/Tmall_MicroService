package com.micro.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude =
{ RabbitAutoConfiguration.class })
public class ProtalApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ProtalApplication.class, args);
	}
}
