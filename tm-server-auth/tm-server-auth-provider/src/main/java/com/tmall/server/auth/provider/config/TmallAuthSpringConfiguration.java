package com.tmall.server.auth.provider.config;
///**
//*
//* @Description
//* @author joker 
//* @date 创建时间：2018年7月29日 下午10:46:26
//* 
//*/
//package com.tmall.server.auth.server.configuaration;
//
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import com.tmall.common.spring.TmallPropertyPlaceholderConfigurer;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
///**
//* 没必要了,springboot 可以直接采取mvn的方式指定profile
//* @When
//* @Description
//* @Detail
//* @author joker 
//* @date 创建时间：2018年7月29日 下午10:46:26
//*/
//@Configuration
//@Order(1)
//public class TmallAuthSpringConfiguration
//{
//	@Profile("dev")
//	@Bean
//	public TmallPropertyPlaceholderConfigurer configurer()
//	{
//		TmallPropertyPlaceholderConfigurer configurer=new TmallPropertyPlaceholderConfigurer();
//		configurer.setLocation(new PathMatchingResourcePatternResolver().getResource("classpath:application-dev.yml"));
//		return configurer;
//	}
//	@Profile("pro")
//	@Bean
//	public TmallPropertyPlaceholderConfigurer proConfigurer()
//	{
//		TmallPropertyPlaceholderConfigurer tmallPropertyPlaceholderConfigurer=new TmallPropertyPlaceholderConfigurer();
//		tmallPropertyPlaceholderConfigurer.setLocation(new PathMatchingResourcePatternResolver().getResource("classpath:application-pro.yml"));
//		return tmallPropertyPlaceholderConfigurer;
//	}
//
//}
