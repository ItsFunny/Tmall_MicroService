/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午10:46:26
* 
*/
package com.tmall.system.sso.config;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月29日 下午10:46:26
 */
@Configuration
@Order(1)
public class TmallSSOSpringConfiguration
{
	@Profile("dev")
	@Bean
	public TmallPropertyPlaceholderConfigurer configurer()
	{
		TmallPropertyPlaceholderConfigurer configurer = new TmallPropertyPlaceholderConfigurer();
		configurer.setLocation(new PathMatchingResourcePatternResolver().getResource("classpath:application-dev.yml"));
		return configurer;
	}

	@Profile("pro")
	@Bean
	public TmallPropertyPlaceholderConfigurer proConfigurer()
	{
		TmallPropertyPlaceholderConfigurer tmallPropertyPlaceholderConfigurer = new TmallPropertyPlaceholderConfigurer();
		tmallPropertyPlaceholderConfigurer
				.setLocation(new PathMatchingResourcePatternResolver().getResource("classpath:application-pro.yml"));
		return tmallPropertyPlaceholderConfigurer;
	}

}
