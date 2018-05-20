/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午2:47:41
* 
*/
package com.micro.portal.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.micro.portal.utils.CookieUtils;
import com.tmall.common.constants.CookieConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.service.INosqlService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午2:47:41
 */
@Controller
public class LoginController
{
	@Autowired
	private INosqlService redisService;
	@Autowired
	private RestTemplate restTemplate;

	
	@RequestMapping("/login")
	public ModelAndView login()
	{
		
		return null;
	}
	/**
	 * 登录服务登录成功后,会跳转到此页面,并且携带一个token,根据token去校验用户token是否过期
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author joker
	 * @date 创建时间：2018年5月17日 下午2:48:54
	 */
	@RequestMapping("/portal/login-notify")
	public ModelAndView portalLogin(@Valid String token, BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView modelAndView = null;
		// String redirectUrl = StringUtils.isEmpty(request.getParameter("redirectUrl"))
		// ? TmallURLConstant.TMALAL_PORTAL_INDEX_URL
		// : request.getParameter("redirectUrl");
		if (bindingResult.hasErrors())
		{
			throw new RuntimeException(bindingResult.getAllErrors().toString());
		}

		// 这里进行token校验的时候,设置自己的cookie值,将其加入白名单
		// 这里应该返回一个boolean值即可,true的话直接利用这个token从redis中获取用户信息
		CookieUtils.setUserToken(response, CookieConstant.USER_TOKEN+"_PORTAL", UUID.randomUUID().toString(),
				CookieConstant.USER_TOKEN_EXPIRE);
		Map<String, Object>params=new HashMap<String, Object>();
		params.put("serverName", "portal");
		Boolean isAuth =restTemplate.postForObject(TmallURLConstant.TMALL_LOGIN_SSO_CHECKLOGIN_URL, request, boolean.class, params);
//		Boolean isAuth = restTemplate.postForObject(TmallURLConstant.TMALL_LOGIN_SSO_VERIFY_URL, ,
//				Boolean.class);
		if (isAuth)
		{
			// 进入首页,带着用户信息
			modelAndView = new ModelAndView("redirect:/index");
		} else
		{
			modelAndView = new ModelAndView(TmallURLConstant.TMALL_LOGIN_URL);
		}
		return modelAndView;
	}

}
