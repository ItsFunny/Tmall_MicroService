package com.tmall.server.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.tmall")
@ComponentScan(basePackages="com.tmall")
public class TmallAuthServer 
{
    public static void main( String[] args )
    {
    	new SpringApplication(TmallAuthServer.class).run(args);
    }
}
