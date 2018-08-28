package com.tmall.server.user.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude= {
		RabbitAutoConfiguration.class
})
@ComponentScan(basePackages="com.tmall")
public class TmallUserAppServer 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(TmallUserAppServer.class).run(args);
    }
}
