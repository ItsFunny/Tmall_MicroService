///**
//*
//* @author joker 
//* @date 创建时间：2018年5月21日 下午9:28:52
//* 
//*/
//package org.login_server.config;
//
//import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//
//import java.util.Map;
//
//import javax.servlet.Filter;
//
//import org.apache.shiro.mgt.DefaultSecurityManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.login_server.filter.LoginFilter;
//import org.login_server.realms.TmallLoginRealm;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.tmall.common.constants.TmallURLConstant;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年5月21日 下午9:28:52
// */
//@Configuration
//public class TmallShiroConfig
//{
//
//	@Bean
//	public TmallLoginRealm loginRealm()
//	{
//		TmallLoginRealm realm = new TmallLoginRealm();
//		return realm;
//	}
//
//	@Bean
//	public ShiroFilterFactoryBean shiroFilterFactoryBean()
//	{
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//		filters.put("checkLogin", new LoginFilter());
//		Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
//		filterChainDefinitionMap.put("/static/**", "anon");
//		filterChainDefinitionMap.put("/js/**", "anon");
//		filterChainDefinitionMap.put("/css/**", "anon");
//
//		filterChainDefinitionMap.put("/order/**", "checkLogin");
//		shiroFilterFactoryBean.setLoginUrl(TmallURLConstant.TMALL_LOGIN_URL);
//
//		return shiroFilterFactoryBean;
//	}
//
//	@Bean
//	public TmallLoginRealm tmallLoginRealm()
//	{
//		return new TmallLoginRealm();
//	}
//
//}
