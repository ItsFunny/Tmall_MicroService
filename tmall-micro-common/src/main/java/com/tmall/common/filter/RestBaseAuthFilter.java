/**
*
* @Description
* @author joker 
* @date 创建时间：2018年6月27日 下午8:12:47
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

import com.tmall.common.auth.AuthKey;
import com.tmall.common.utils.JsonUtils;
import com.tmall.common.utils.RSAUtils;
import com.tmall.common.utils.ResultUtils;
/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年6月27日 下午8:12:47
*/
public class RestBaseAuthFilter implements Filter
{
	private AuthKey authKey;
	
	@Override
	public void destroy()
	{
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException
	{
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		String encryptToken = request.getParameter("token");
		if(StringUtils.isEmpty(request.getQueryString())||StringUtils.isEmpty(encryptToken))
		{
			response.getWriter().write(JsonUtils.obj2Json(ResultUtils.fail("mising token argument")));
			return;
		}
		String string = RSAUtils.decryptByPrivate(encryptToken, new String(authKey.getAuthPrivateKey()));
		if(StringUtils.isEmpty(string))
		{
			response.getWriter().write(JsonUtils.obj2Json(ResultUtils.fail("illegal token argument")));
			response.setStatus(HttpStatus.UNAUTHORIZED.ordinal());
			return;
		}
		filterChain.doFilter(req, resp);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		try
		{
			authKey=AuthKey.getAuthKey();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
