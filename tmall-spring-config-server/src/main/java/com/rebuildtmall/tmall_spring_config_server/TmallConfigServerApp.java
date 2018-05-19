package com.rebuildtmall.tmall_spring_config_server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class TmallConfigServerApp
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(TmallConfigServerApp.class).run(args);
    }
}
