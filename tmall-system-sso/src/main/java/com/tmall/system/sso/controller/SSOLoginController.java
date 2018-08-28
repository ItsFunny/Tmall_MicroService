/**
*
* @author joker 
* @date 创建时间：2018年8月4日 下午11:27:29
* 
*/
package com.tmall.system.sso.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.reflect.TypeToken;
import com.tmall.common.constants.AuthConstant;
import com.tmall.common.constants.RedisConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JsonUtils;
import com.tmall.serer.spi.gateway.feign.user.IGatewayUserFeignService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月4日 下午11:27:29
 */
@Controller
public class SSOLoginController
{
	private Logger logger = LoggerFactory.getLogger(SSOLoginController.class);
	@Autowired
	private INosqlService redisService;
	@Autowired
	private IGatewayUserFeignService gatewayUserFeignService;
//	private String getServerUrl(String name)
//	{
//		ServiceInstance serviceInstance = loadBalancerClient.choose(name);
//		String host = serviceInstance.getHost();
//		int port = serviceInstance.getPort();
//		return host + ":" + port;
//	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException
	{
		ModelAndView modelAndView = null;
		String storeAbbName = request.getParameter("storeAbbName");
		storeAbbName = StringUtils.isEmpty(storeAbbName) ? "tmall_admin" : storeAbbName;

		String redirectUrl = request.getParameter("redirectUrl");
		String error = request.getParameter("error");
		redirectUrl = StringUtils.isEmpty(redirectUrl) ? TmallURLConstant.TMALL_PORTAL_INDEX_URL
				: URLDecoder.decode(redirectUrl, "utf-8");
		modelAndView = new ModelAndView("login");
		modelAndView.addObject("redirectUrl", redirectUrl);
		modelAndView.addObject("error", error);
		modelAndView.addObject("storeAbbName", "tmall_admin");
		return modelAndView;
	}

	@RequestMapping("/login.do")
	public ModelAndView doLogin(
			@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		ModelAndView modelAndView = null;
		String storeAbbName = request.getParameter("storeAbbName");
		String redirectUrl=request.getParameter("redirectUrl");
		String loginKey=request.getParameter("loginKey");
		String password=request.getParameter("password");
		if(StringUtils.isEmpty(loginKey)|| StringUtils.isEmpty(password))
		{
			params.put("error", "账户名称或者密码不能为空");
		}
		if (StringUtils.isEmpty(storeAbbName))
		{
			params.put("error", "店铺缩写名称不能为空");
		}
		if (params.containsKey("error"))
		{
			modelAndView = new ModelAndView("login", params);
			return modelAndView;
		}
		ResultDTO<String> resultDTO = gatewayUserFeignService.loginAndAuth(loginKey, password, storeAbbName);
		if (resultDTO.isSuccess())
		{
			// 返回到请求的页面中,并且携带token
			HttpSession session = request.getSession(true);
			session.setAttribute(AuthConstant.AUTH_HEADER, resultDTO.getData());
			try
			{
				Set<String> systems = new HashSet<>();
				String key = String.format(RedisConstant.USER_LOGIN_SYSTEMS, session.getId());
				String json = redisService.get(key);
				if (!StringUtils.isEmpty(json))
				{
					systems.addAll(JsonUtils.json2List(json, new TypeToken<String>()
					{
					}.getType()));
				}
				systems.add(storeAbbName);
				redisService.set(key, JsonUtils.obj2Json(systems));
			} catch (Exception e)
			{
				logger.error("redis服务器挂了", e);
			}
//			AuthTokenDTO tokenDTO = jwtUtils.parseByAuthPublicKey(resultDTO.getData());
			modelAndView = new ModelAndView("redirect:"+redirectUrl);
			modelAndView.addObject("token",URLEncoder.encode(resultDTO.getData(),"utf-8"));
		} else
		{
			params.put("error", resultDTO.getMsg());
			modelAndView = new ModelAndView("login", params);
		}
		return modelAndView;
	}
}
