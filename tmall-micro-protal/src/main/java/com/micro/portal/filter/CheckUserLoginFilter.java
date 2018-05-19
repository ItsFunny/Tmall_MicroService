/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午4:59:02
* 
*/
package com.micro.portal.filter;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.micro.portal.config.KeyProperties;
import com.micro.portal.utils.PortalUtils;
import com.tmall.common.constants.RedisConstant;
import com.tmall.common.constants.SecurityConstant;
import com.tmall.common.constants.SessionConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.model.User;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JsonUtils;
import com.tmall.common.utils.RSAUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午4:59:02
 */
public class CheckUserLoginFilter implements Filter
{
	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private INosqlService redisService;

	public CheckUserLoginFilter(KeyProperties keyProperties, INosqlService redisService)
	{
		this.keyProperties = keyProperties;
		this.redisService = redisService;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	// @Override
	// public void doFilter(ServletRequest request, ServletResponse response,
	// FilterChain chain)
	// throws IOException, ServletException
	// {
	// HttpServletRequest req = (HttpServletRequest) request;
	//
	// HttpServletResponse resp = (HttpServletResponse) response;
	// HttpSession session = req.getSession(true);
	// Object isLogin = session.getAttribute("isLogin");
	// if (null == isLogin || !(boolean) isLogin)
	// {
	// RestTemplate restTemplate = new RestTemplate();
	// StringBuffer urlBuffer = req.getRequestURL();
	// String queryString = req.getQueryString();
	// String urlStr = null;
	// urlStr = StringUtils.isEmpty(queryString) ? urlBuffer.toString()
	// : urlBuffer.append("?").append(queryString).toString();
	//
	//// String headerPrimitive = req.getHeader(SecurityConstant.HEADER_PRIMITIVE);
	//// String sign = req.getHeader(SecurityConstant.HEADER_SIGN);
	// String headerPrimitive=req.getParameter("primitive");
	// String sign=req.getParameter("sign");
	// if(StringUtils.isEmpty(headerPrimitive)||StringUtils.isEmpty(sign))
	// {
	// resp.sendRedirect(TmallURLConstant.TMALL_LOGIN_URL + "?redirectUrl=" +
	// urlStr);
	// return;
	// }
	// boolean isVerify = RSAUtils.verify(headerPrimitive, new
	// String(keyProperties.getLoginPublicKey()), sign);
	// if(!isVerify)
	// {
	// resp.sendRedirect(TmallURLConstant.TMALL_LOGIN_URL + "?redirectUrl=" +
	// urlStr);
	// return;
	// }
	// String token = req.getParameter("token");
	// // 不一定会一直都携带着一个token,因为:有个isLogin啊,他代替了token的作用啊
	// String url = TmallURLConstant.TMALL_LOGIN_SSO_CHECKLOGIN_URL;
	// if (!StringUtils.isEmpty(token))
	// {
	// url += "?token=" + token;
	// }
	//
	// boolean isAuth = restTemplate.getForObject(url, Boolean.class);
	// if (isAuth)
	// {
	// session.setAttribute("isLogin", true);
	// } else
	// {
	// resp.sendRedirect(TmallURLConstant.TMALL_LOGIN_URL + "?redirectUrl=" +
	// urlStr);
	// return;
	// }
	// }
	// chain.doFilter(request, response);
	// }

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		Object isAuth = session.getAttribute(SessionConstant.markIsAuth);
		if (null != isAuth && (boolean) isAuth)
		{
			chain.doFilter(req, resp);
			return;
		}
		String token = request.getParameter("token");
		if (!StringUtils.isEmpty(token))
		{
			// 校验token的有效性
			RestTemplate restTemplate = new RestTemplate();
			String json = restTemplate.getForObject(TmallURLConstant.TMALL_LOGIN_SSO_AUTH_TOKEN_URL + "?token=" + URLEncoder.encode(token, "utf-8"),
					String.class);
			if (!StringUtils.isEmpty(json))
			{
				User user = JsonUtils.json2Object(json, User.class);
				session.setAttribute(SessionConstant.markIsAuth, true);
				session.setAttribute(SessionConstant.USER_IN_SESSION, user);
				chain.doFilter(req, resp);
				return;
			}
		}
		//传入的token无效的时候也会跳转到查询用户是否登录
		HttpServletResponse response = (HttpServletResponse) resp;
		// 查询用户是否登录
		response.sendRedirect(TmallURLConstant.TMALL_LOGIN_SSO_CHECKLOGIN_URL + "?redirectUrl="
				+ PortalUtils.getRedirectUrl(request));
	}
}
