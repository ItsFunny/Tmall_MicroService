package com.tmall.server.product;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.tmall.server.product.config.ProductAPPServerConfiguraiton;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.tmall.server.product", excludeFilters =
{ @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Mapper.class) })
@EnableFeignClients(basePackages= {"com.tmall.server.spi"})
//扫描的是第一个db的mapper文件
@MapperScan(basePackages = "com.tmall.server.product.dao.db0", annotationClass = Mapper.class,sqlSessionFactoryRef="sqlSessionOne")
@Import(ProductAPPServerConfiguraiton.class)
public class TmallProductServerApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TmallProductServerApplication.class).run(args);
	}
}
