/**
*
* @author joker 
* @date 创建时间：2018年8月17日 下午10:51:36
* 
*/
package com.tmall.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.utils.JWTUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月17日 下午10:51:36
 */

/*
 * 2018-08-18 先留着吧,现在是用interceptor代替这个filter
 * 之所以考虑留着是因为用户未登录的情况是直接在这里跳转呢还是返回异常,
 * 客户端异常处理再跳转
 */
@Deprecated
public class RestJWTAuthFilter implements Filter
{

	@Autowired
	private JWTUtils jwtUtil;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub

	}

	@Autowired
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		String header = req.getHeader(AuthConstant.AUTH_HEADER);
		if (StringUtils.isEmpty(header))
		{
			throw new TmallBussinessException(TmallBussinessException.UNAUTHENTICATED_EXCEPTION, "用户尚未登录");
		} else
		{
			try
			{
				AuthTokenDTO dto = jwtUtil.parseByAuthPublicKey(header);
				if (System.currentTimeMillis() > dto.getInvalidTime())
				{
					throw new TmallBussinessException(TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION, "用户身份信息已过期");
				} else
				{
					chain.doFilter(request, response);
					return;
				}
			} catch (Exception e)
			{
				throw new TmallBussinessException(TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION, "用户身份信息已过期");
			}
		}
	}

	@Override
	public void destroy()
	{

	}

}
