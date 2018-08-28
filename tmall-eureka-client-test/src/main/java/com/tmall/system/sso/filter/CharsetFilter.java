/**
*
* @author joker 
* @date 创建时间：2018年6月10日 下午3:27:34
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

import org.springframework.web.bind.annotation.RestController;

/**
* 
* @author joker 
* @date 创建时间：2018年6月10日 下午3:27:34
*/
public class CharsetFilter implements Filter
{
	private String encoding;
	@Override
	public void destroy()
	{
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException
	{
		if(encoding!=null)
		{
			resp.setCharacterEncoding(encoding);
			resp.setContentType("text/html;charset="+encoding);
		}
		filterChain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.encoding=filterConfig.getInitParameter("encoding");
	}

}
