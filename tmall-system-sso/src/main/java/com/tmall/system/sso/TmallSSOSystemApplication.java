package com.tmall.system.sso;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.tmall.server.spi"})
public class TmallSSOSystemApplication 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(TmallSSOSystemApplication.class).run(args);
    }
}
