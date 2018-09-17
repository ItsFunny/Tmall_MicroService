package com.tmall.server.message;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.tmall.server.message.config.TmallMessageServerConfiguration;

@SpringBootApplication(exclude =
{ RabbitAutoConfiguration.class })
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.tmall.server.spi")
@Import(TmallMessageServerConfiguration.class)
public class TmMessageApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TmMessageApplication.class).run(args);
	}
}
