/**
*
* @author joker 
* @date 创建时间：2018年5月24日 下午2:04:11
* 
*/
package com.micro.portal.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
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
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.micro.portal.filter.CheckUserLoginFilter;
import com.micro.portal.shiro.credential.PortalLoginCredentialMatcher;
import com.micro.portal.shiro.realm.PortalLoginRealm;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.service.INosqlService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月24日 下午2:04:11
 */
@Configuration
@Import(TmallPortalConfig.class)
public class TmallPortalShiroConfig implements WebMvcConfigurer
{
	
	
	@Bean
	public SecurityManager securityManager()
	{
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(portalLoginRealm());
		return securityManager;
	}

	@Bean
	public PortalLoginRealm portalLoginRealm()
	{
		PortalLoginRealm realm = new PortalLoginRealm();
		realm.setCredentialsMatcher(loginMatcher());
		return realm;
	}

	@Bean
	public CredentialsMatcher loginMatcher()
	{
		PortalLoginCredentialMatcher matcher = new PortalLoginCredentialMatcher();
		return matcher;
	}



	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(KeyProperties keyProperties, INosqlService redisService)
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("authc", new CheckUserLoginFilter(keyProperties, redisService));
		shiroFilterFactoryBean.setLoginUrl(TmallURLConstant.TMALL_LOGIN_URL);
		Map<String, String> definitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
		definitionMap.put("/test", "anon");
		definitionMap.put("/static/**", "anon");
		definitionMap.put("/order/**", "authc");
		definitionMap.put("/user/**", "authc");
		return shiroFilterFactoryBean;
	}

//	@Bean
//	public CheckUserLoginFilter checkUserLoginFilter(KeyProperties keyProperties,INosqlService redisService,FeginLoginServerService serverService)
//	{
//		return new CheckUserLoginFilter(keyProperties, redisService, serverService);
//	}
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
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(31536000);
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(31536000);
	}
}
