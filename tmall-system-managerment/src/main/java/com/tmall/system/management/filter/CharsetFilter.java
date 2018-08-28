/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午3:47:25
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
import javax.servlet.http.HttpServletResponse;

/**
* 
* @author joker 
* @date 创建时间：2018年6月21日 下午3:47:25
*/
public class CharsetFilter implements Filter
{
	private String encoding;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.encoding=filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		if(null!=encoding)
		{
			resp.setCharacterEncoding(encoding);
			resp.setContentType("text/html;charset=utf-8");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

}
