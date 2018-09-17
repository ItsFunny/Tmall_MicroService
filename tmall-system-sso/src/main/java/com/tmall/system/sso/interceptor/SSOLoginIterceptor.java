/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月17日 下午9:10:38
* 
*/
package com.tmall.system.sso.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.constants.AuthConstant;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月17日 下午9:10:38
 */
public class SSOLoginIterceptor implements HandlerInterceptor
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		HttpSession session = request.getSession(true);
		Object tokenObj = session.getAttribute(AuthConstant.AUTH_IN_SESSION);
		if(null==tokenObj)
		{
			return true;
		}
		String token=tokenObj.toString();
		String redirectUrl=StringUtils.defaultString(request.getParameter("redirectUrl"), "");
		response.sendRedirect(redirectUrl+"&token="+URLEncoder.encode(token,"utf-8"));
		return false;
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
