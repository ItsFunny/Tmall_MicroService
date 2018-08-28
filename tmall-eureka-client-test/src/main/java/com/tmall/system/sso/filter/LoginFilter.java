/**
*
* @author joker 
* @date 创建时间：2018年6月9日 下午2:10:19
* 
*/
package com.tmall.system.sso.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.StringUtils;
import com.tmall.common.constants.CookieConstant;
import com.tmall.common.constants.SessionConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.system.sso.utils.CookieUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年6月9日 下午2:10:19
*/
public class LoginFilter implements Filter ,BeanFactoryAware
{
	private BeanFactory beanFactory;

	@Override
	public void destroy()
	{
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException
	{
		HttpServletRequest request=(HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		if(null==session.getAttribute(SessionConstant.markIsAuth))
		{
			filterChain.doFilter(req, resp);
			return;
		}
		String token = CookieUtils.getUserToken(request, CookieConstant.USER_TOKEN);
		String redirectUrl = request.getParameter("reidrectUrl");
		redirectUrl=StringUtils.isEmpty(redirectUrl)?TmallURLConstant.TMALL_PORTAL_INDEX_URL:redirectUrl;
		HttpServletResponse response=(HttpServletResponse) resp;
		response.sendRedirect(redirectUrl+"token="+token);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException
	{
		this.beanFactory=arg0;
	}

}
