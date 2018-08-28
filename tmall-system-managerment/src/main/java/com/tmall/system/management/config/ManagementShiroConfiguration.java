/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午7:28:52
* 
*/
package com.tmall.system.management.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.tmall.system.management.filter.TempFilter;
import com.tmall.system.management.shiro.realm.ManagementAuthorizingRealm;

/**
* 
* @author joker 
* @date 创建时间：2018年6月21日 下午7:28:52
*/
@Configuration
public class ManagementShiroConfiguration
{
	@Bean
	public ManagementAuthorizingRealm managementAuthorizingRealm()
	{
		return new ManagementAuthorizingRealm();
	}
	@Bean
	public SecurityManager securityManager()
	{
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(managementAuthorizingRealm());
		return securityManager;
	}
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean()
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		shiroFilterFactoryBean.setLoginUrl("www.baidu.com");
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("authc", new TempFilter());
		Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
		filterChainDefinitionMap.put("/test", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		return shiroFilterFactoryBean;
	}
	@Bean(name="lifeCycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
	{
		return new LifecycleBeanPostProcessor();
	}
	/*
	 * 将securityManger绑定
	 */
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean()
	{
		MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
		factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		factoryBean.setArguments(securityManager());
		return factoryBean;
	}
	/*
	 * 开启注解支持
	 */
	@DependsOn(value= {"lifeCycleBeanPostProcessor"})
	@Bean(name = "defaultAdvisorAutoProxyCreator")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
	{
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager)
	{
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
