package com.tmall.server.auth.provider;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.tmall.server.auth.provider.config.TmallAuthServerConfiguration;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages="com.tmall",scanBasePackageClasses=TmallAuthServerConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.tmall.server.spi"})
@MapperScan(basePackages="com.tmall",annotationClass=Mapper.class)
public class TmallAuthServerApplicaiton 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(TmallAuthServerApplicaiton.class).run(args);
    }
}
