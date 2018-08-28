/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月23日 上午11:28:33
* 
*/
package com.tmall.system.sso.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import com.google.gson.reflect.TypeToken;
import com.tmall.common.constants.AuthConstant;
import com.tmall.common.constants.RedisConstant;
import com.tmall.common.constants.SessionConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JWTUtils;
import com.tmall.common.utils.JsonUtils;
import com.tmall.server.spi.auth.IAuthFeignService;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月23日 上午11:28:33
 */
public class SSOLoginFilter implements Filter
{
	private Logger logger=LoggerFactory.getLogger(SSOLoginFilter.class);
	
	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private INosqlService redisService;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	private String getServerUrl(String name)
	{
		ServiceInstance serviceInstance = loadBalancerClient.choose(name);
		String host = serviceInstance.getHost();
		int port = serviceInstance.getPort();
		return host + ":" + port;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		/*
		 * 子系统发现用户尚未登录,则试图跳转到登录页面 filter 拦截,全局session发
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession(true);
		String authToken = (String) session.getAttribute(SessionConstant.AUTH_TOKEN);
		if (StringUtils.isEmpty(authToken))
		{
			chain.doFilter(request, response);
			return;
		}
		
		AuthTokenDTO authTokenDTO = jwtUtils.parseByAuthPublicKey(authToken);
		if (System.currentTimeMillis() > authTokenDTO.getInvalidTime())
		{
			chain.doFilter(request, response);
			return;
		}
		String redirectUrl = req.getParameter("redirectUrl");
		redirectUrl = StringUtils.isEmpty(redirectUrl) ? getServerUrl("ADMIN")
				: URLDecoder.decode(redirectUrl, "utf-8");
		String storeAbbName = req.getParameter("storeAbbName");
		storeAbbName = StringUtils.isEmpty(storeAbbName) ? "tmall_admin" : storeAbbName;
		/*
		 * 将用户登录着的子系统的信息保存到redis中,方便统一下线 数据结构是一个set
		 */
//		try
//		{
//			Set<String> systems = new HashSet<>();
//			String key = String.format(RedisConstant.USER_LOGIN_SYSTEMS, session.getId());
//			String json = redisService.get(key);
//			if (!StringUtils.isEmpty(json))
//			{
//				systems.addAll(JsonUtils.json2List(json, new TypeToken<String>()
//				{
//				}.getType()));
//			}
//			systems.add(storeAbbName);
//			redisService.set(key, JsonUtils.obj2Json(systems));
//		} catch (Exception e)
//		{
//			logger.error("redis服务器挂了",e);
//		}
		resp.addHeader(AuthConstant.AUTH_HEADER, authToken);
		resp.sendRedirect(redirectUrl);
		authToken=URLEncoder.encode(authToken,"utf-8");
		redirectUrl+=redirectUrl.contains("&")?"&token="+authToken:"?token="+authToken;
		resp.sendRedirect(redirectUrl);
		// 如果已经认证过了,则校验token是否过期
		// boolean isInvalid = jwtUtils.checkToken(authToken.toString());
		// if(isInvalid)
		// {
		// //跳转到获取token的页面
		// resp.sendRedirect("/auth/token");
		//
		// }else {
		// resp.sendRedirect(redirectUrl);
		// resp.addHeader("","");
		// }

	}

	@Override
	public void destroy()
	{

	}

}
