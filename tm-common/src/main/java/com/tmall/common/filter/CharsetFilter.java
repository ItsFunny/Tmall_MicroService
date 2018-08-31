/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午8:33:09
* 
*/
package com.tmall.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月26日 下午8:33:09
*/
public class CharsetFilter implements Filter
{
	private String encoding;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		if(null!=encoding)
		{
			response.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset="+encoding);
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
		
	}

}
