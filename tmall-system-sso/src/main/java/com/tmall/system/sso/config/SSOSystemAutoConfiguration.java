/**
*
* @author joker 
* @date 创建时间：2018年6月3日 下午10:06:56
* 
*/
package com.tmall.system.sso.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.tmall.common.filter.CharsetFilter;
import com.tmall.common.utils.JWTUtilFactoryBean;
import com.tmall.common.utils.KeyProperty;
import com.tmall.system.sso.filter.SSOLoginFilter;
import com.tmall.system.sso.interceptor.SSOLoginIterceptor;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月3日 下午10:06:56
 */
@Configuration
@EnableConfigurationProperties(value =
{ KeyProperty.class })
public class SSOSystemAutoConfiguration implements WebMvcConfigurer
{
	@Autowired
	private KeyProperty keyProperty;

	@Bean
	public JWTUtilFactoryBean jwtUtilFactoryBean()
	{
		JWTUtilFactoryBean factoryBean = new JWTUtilFactoryBean(keyProperty.getAuthPrivateKey(),
				keyProperty.getAuthPublicKey(), keyProperty.getSsoPrivateKey(), keyProperty.getSsoPublicKey());
		return factoryBean;
	}

	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean()
	{
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CharsetFilter());
		registrationBean.addInitParameter("encoding", "utf-8");
		registrationBean.setUrlPatterns(Arrays.asList("/*"));
		registrationBean.setOrder(0);
		return registrationBean;
	}

	// @Bean
	// public FilterRegistrationBean<Filter> loginFilterRegistrationBean()
	// {
	// FilterRegistrationBean<Filter> filterRegistrationBean = new
	// FilterRegistrationBean<>();
	// filterRegistrationBean.setFilter(new SSOLoginFilter());
	// filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
	// return filterRegistrationBean;
	// }

	@Bean
	public ViewResolver viewResolver()
	{
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer()
	{
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/templates/");
		return configurer;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:static/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new SSOLoginIterceptor()).addPathPatterns(Arrays.asList("/*"))
				.excludePathPatterns(Arrays.asList("/doLogin"));
	}

}
