/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午6:27:57
* 
*/
package com.tmall.system.management.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.common.constants.SessionConstant;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JsonUtils;
import com.tmall.common.utils.RSAUtils;
import com.tmall.system.management.config.KeyProperty;

/**
* 
* @author joker 
* @date 创建时间：2018年6月21日 下午6:27:57
*/
public class CheckLoginFilter implements Filter,BeanFactoryAware
{
	private BeanFactory beanFactory;
	
	private INosqlService redisService;
	
	private KeyProperty keyProperty;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request=(HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		Object markIsAuth = session.getAttribute(SessionConstant.markIsAuth);
		if(null!=markIsAuth)
		{
			chain.doFilter(req, resp);
			return;
		}
		String encryptToken = request.getParameter("token");
		String previousToken = RSAUtils.decryptByPublic(encryptToken, keyProperty.getLoginPublicKey());
		String userJson = redisService.get(previousToken);
		UserDTO user = JsonUtils.json2Object(userJson, UserDTO.class);
		
	}

	@Override
	public void destroy()
	{
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException
	{
		this.beanFactory=beanFactory;
		this.redisService=beanFactory.getBean(INosqlService.class);
		this.keyProperty=beanFactory.getBean(KeyProperty.class);
	}

}
