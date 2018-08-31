/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午3:08:42
* 
*/
package com.tmall.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.exception.TmallUserException;
import com.tmall.common.utils.JWTUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月18日 下午3:08:42
 */
public class RestAuthTokenInterceptor implements HandlerInterceptor
{
	@Autowired
	private JWTUtils jwtUtil;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		HttpServletRequest req = (HttpServletRequest) request;
		String header = req.getHeader(AuthConstant.AUTH_HEADER);
		if (StringUtils.isEmpty(header))
		{
			response.setStatus(HttpStatus.UNAUTHORIZED.ordinal());
			throw new TmallUserException(TmallBussinessException.UNAUTHENTICATED_EXCEPTION,"用户尚未登录");
		} else
		{
			try
			{
				AuthTokenDTO dto = jwtUtil.parseByAuthPublicKey(header);
				if (System.currentTimeMillis() < dto.getInvalidTime())
				{
					return true;
				}
				response.setStatus(TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION);
				throw new TmallUserException(TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION, "用户身份信息已过期");
			} catch (Exception e)
			{
				response.setStatus(TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION);
				throw new TmallUserException(TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION, "无效的用户身份信息");
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
