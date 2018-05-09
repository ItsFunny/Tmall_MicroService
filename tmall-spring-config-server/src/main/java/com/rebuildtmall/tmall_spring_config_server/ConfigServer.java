package com.rebuildtmall.tmall_spring_config_server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(ConfigServer.class).run(args);
    }
}
