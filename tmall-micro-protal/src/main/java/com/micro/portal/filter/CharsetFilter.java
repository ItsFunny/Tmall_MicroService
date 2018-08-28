/**
*
* @author joker 
* @date 创建时间：2018年5月27日 下午2:59:45
* 
*/
package com.micro.portal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Encoded;

/**
* 
* @author joker 
* @date 创建时间：2018年5月27日 下午2:59:45
*/
public class CharsetFilter implements Filter
{
	private String encoding=null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		encoding=filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		if(encoding!=null)
		{
			response.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset="+encoding);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
		
	}

}
