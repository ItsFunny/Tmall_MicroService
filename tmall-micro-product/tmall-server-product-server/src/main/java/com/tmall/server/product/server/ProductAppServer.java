package com.tmall.server.product.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages="com.tmall")
@EnableAspectJAutoProxy
public class ProductAppServer	 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(ProductAppServer.class).run(args);
    }
}
