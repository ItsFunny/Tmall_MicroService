package com.tmall.system.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.tmall.serer.spi.product","com.tmall.server.spi.gateway.feign"})
@EnableCaching
@ComponentScan(basePackages="com.tmall")
public class TmallAdminSystem 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(TmallAdminSystem.class).run(args);
    }
}
