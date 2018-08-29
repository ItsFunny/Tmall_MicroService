package com.tmall.gateway.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableAutoConfiguration(exclude= {RabbitAutoConfiguration.class})
@ComponentScan(basePackages="com.tmall")
@EnableFeignClients(basePackages="com.tmall.server.spi")
public class GatewayServer 
{
    public static void main(String[] args )
    {
//    	HystrixCommandProperties
    	new SpringApplicationBuilder(GatewayServer.class).run(args);
    }
    
}
