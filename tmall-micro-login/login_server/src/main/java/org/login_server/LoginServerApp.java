package org.login_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableAutoConfiguration(exclude =
{ RabbitAutoConfiguration.class })
public class LoginServerApp
{
	public static void main(String[] args)
	{
		SpringApplication.run(LoginServerApp.class, args);
	}
}
