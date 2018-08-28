package com.tmall.system.management;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients(basePackages= {
		"com.tmall.gateway.client.service"
})
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude= {
		RabbitAutoConfiguration.class,
})
public class ManagementSystem 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(ManagementSystem.class).run(args);
    }
}
