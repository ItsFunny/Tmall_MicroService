/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月17日 下午6:45:24
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

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.joker.library.utils.JsonUtil;
import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.AuthTokenDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月17日 下午6:45:24
*/
public class SSOLoginFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		Object tokenObj = session.getAttribute(AuthConstant.AUTH_IN_SESSION);
		if(null==tokenObj)
		{
			chain.doFilter(request, response);
			return;
		}
		AuthTokenDTO tokenDTO = JsonUtil.json2Object(tokenObj.toString(), AuthTokenDTO.class);
		if(System.currentTimeMillis()>tokenDTO.getInvalidTime())
		{
			chain.doFilter(request, response);
			return;
		}
		//设置默认的成功地址
		String redirectUrl=StringUtils.defaultString(req.getParameter("redirectUrl"), "");
		resp.sendRedirect(redirectUrl+"&token="+tokenObj);
		return;
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

}
