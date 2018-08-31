/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午6:27:54
* 
*/
package com.tmall.system.admin.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tmall.system.admin.filter.AuthUrlFilter;
import com.tmall.system.admin.shiro.TmallAuthRealm;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月26日 下午6:27:54
 */
@Configuration
@EnableConfigurationProperties(value= {
		TmallAdminConfigProperty.class
})
public class AdiminSystemSpringConfiguration 
{
	
	
//	@Bean
//	public TmallErrorDecoder tmallErrorDecoder()
//	{
//		return new TmallErrorDecoder();
//	}

	@Bean
	public TmallAuthRealm tmallAuthRealm()
	{
		TmallAuthRealm tmallAuthRealm=new TmallAuthRealm();

		tmallAuthRealm.setCachingEnabled(true);
		return tmallAuthRealm;
	}
	@Bean
	public CacheManager cacheManager()
	{
		EhCacheManager ehCacheManager=new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:shiro/cacheManager.xml");
		return ehCacheManager;
	}
//	@Bean
//	public org.springframework.cache.CacheManager tmallAdminCacheManager()
//	{
//		CompositeCacheManager cacheManager=new CompositeCacheManager();
//		List<org.springframework.cache.CacheManager>cacheManagers=new ArrayList<>();
//		cacheManagers.add(new ConcurrentMapCacheManager("menus"));
//		return cacheManager;
//	}
	@Bean
	public org.springframework.cache.CacheManager tmallMenuCacheManager()
	{
		ConcurrentMapCacheManager manager=new ConcurrentMapCacheManager("menus");
		return manager;
	}
	@Bean
	public SecurityManager securityManager()
	{
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(tmallAuthRealm());
		securityManager.setCacheManager(cacheManager());
		return securityManager;
	}
	@Bean
	public AuthUrlFilter authFilter()
	{
		return new AuthUrlFilter();
	}
	@Bean
	public FilterRegistrationBean<Filter>filterReg()
	{
		FilterRegistrationBean<Filter>filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(authFilter());
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean()
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("authc", authFilter());
		shiroFilterFactoryBean.setLoginUrl("https://www.baidu.com/");
		filterChainDefinitionMap.put("/**","authc");
		filterChainDefinitionMap.put("/admin/**", "authc");
		return shiroFilterFactoryBean;
	}
}
