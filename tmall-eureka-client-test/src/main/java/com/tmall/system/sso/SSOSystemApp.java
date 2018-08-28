package com.tmall.system.sso;

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
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude =
{ RabbitAutoConfiguration.class })
@EnableFeignClients(basePackages="com.tmall.server.login.spi")
public class SSOSystemApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(SSOSystemApp.class, args);
    }
}
