/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午1:58:38
* 
*/
package com.tmall.server.product.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.util.StringUtils;


/**
* 
* @author joker 
* @date 创建时间：2018年6月24日 下午1:58:38
*/
public class CharsetFilter implements Filter
{

	private String encoding;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		String string = filterConfig.getInitParameter("encoding");
		this.encoding=string;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		if(!StringUtils.isEmpty(encoding))
		{
			response.setCharacterEncoding(encoding);
			response.setContentType("application/json;charset=utf-8");
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

}
