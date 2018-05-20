/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午9:04:14
* 
*/
package org.login_server.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.login_server.config.KeyProperties;
import org.login_server.enums.UserStatusEnum;
import org.login_server.service.IUserService;
import org.login_server.utils.CookieUtils;
import org.login_server.utils.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.constants.CookieConstant;
import com.tmall.common.constants.RedisConstant;
import com.tmall.common.constants.SessionConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.enums.RabbitMQEnum;
import com.tmall.common.event.AppEvent;
import com.tmall.common.impl.UserOffsitePublisher;
import com.tmall.common.model.User;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JsonUtils;
import com.tmall.common.utils.KeyUtils;
import com.tmall.common.utils.RSAUtils;

/**
 * 私钥加密,公钥解密
 * 
 * @author joker
 * @date 创建时间：2018年5月10日 上午9:04:14
 */
@Controller
public class LoginController
{
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private INosqlService redisService;
	@Autowired
	private IUserService userService;

	@Autowired
	private UserOffsitePublisher userOffsitePublisher;

	@Autowired
	private KeyProperties keyProperties;

	/**
	 * 校验其他服务发过来的请求是否登录 这个是需要向外开放的接口,所以应该放在login-client里
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author joker
	 * @date 创建时间：2018年5月17日 下午5:16:25
	 */

	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request)
	{
		// User user = new User();
		// user.setIDCard("asdasd");
		String uri = request.getRequestURL().toString();
		String string = uri.substring(0, uri.length() - request.getRequestURI().length());
		System.out.println(string);
		return request.getRequestURL().toString();
	}
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		String redirectUrl = request.getParameter("redirectUrl");
		redirectUrl = StringUtils.isEmpty(redirectUrl) ? TmallURLConstant.TMALAL_PORTAL_INDEX_URL : redirectUrl;
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("redirectUrl", redirectUrl);
		return modelAndView;
	}
	// function: 校验token有效性,以及认证是否登录
	@RequestMapping("/authToken")
	public void authToken(@RequestBody String token, HttpServletRequest request,
			HttpServletResponse response) 
	{
		PrintWriter writer = null;
		try
		{
			token=URLDecoder.decode(token, "utf-8");
			writer = response.getWriter();
			String primitiveKey = RSAUtils.decryptByPublic(token, keyProperties.getLoginPublicKey());
			String json = redisService.get(String.format(RedisConstant.USER_INFO, primitiveKey));
			writer.write(json);
		} catch (Exception e)
		{
			logger.error("解析token发生异常,时间:{}",new Date());
			writer.write("");
		}finally {
			writer.flush();
			writer.close();
		}
	}
	@RequestMapping("/checkLogin")
	public ModelAndView checkIsLogin(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(true);
		Object isAuth = session.getAttribute(SessionConstant.markIsAuth);
		String redirectUrl = request.getParameter("redirectUrl");
		if (null != isAuth && (boolean) isAuth)
		{
			String encryptToken = CookieUtils.getUserToken(request, CookieConstant.USER_TOKEN);
			if (StringUtils.isEmpty(encryptToken))
			{
				modelAndView = new ModelAndView(
						"redirect:" + TmallURLConstant.TMALL_LOGIN_URL + "?redirectUrl=" + redirectUrl);
			} else
			{
				modelAndView = new ModelAndView("redirect:" + redirectUrl + "token=" + encryptToken);
			}
		} else
		{
			// 说明是第一次登录
			modelAndView = new ModelAndView(
					"redirect:" + TmallURLConstant.TMALL_LOGIN_URL + "?redirectUrl=" + redirectUrl);
		}
		return modelAndView;
	}
	@RequestMapping("/login.do")
	public ModelAndView doLogin(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException
	{
		ModelAndView modelAndView = null;
		String redirectUrl = StringUtils.isEmpty(params.get("redirectUrl"))
				? TmallURLConstant.TMALAL_PORTAL_INDEX_URL
				: (String) params.get("redirectUrl");
		String encryptedToken = CookieUtils.getUserToken(request, CookieConstant.USER_TOKEN);
		if (!StringUtils.isEmpty(encryptedToken))
		{
			String primitiveToken = RSAUtils.decryptByPublic(encryptedToken, keyProperties.getLoginPublicKey());
			if (!StringUtils.isEmpty(primitiveToken)
					&& !StringUtils.isEmpty(redisService.get(String.format(RedisConstant.USER_INFO, primitiveToken))))
			{
				
				HttpSession session = request.getSession();
				System.out.println(session.getId());
				session.setAttribute(SessionConstant.markIsAuth, true);
//				request.getSession(true).setAttribute(SessionConstant.markIsAuth, true);
				String encode = URLEncoder.encode(encryptedToken, "utf-8");
				System.out.println(encode);
				modelAndView = new ModelAndView("redirect:" + redirectUrl + "token=" + encode);
				return modelAndView;
			}
		}
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userService.findByEmail(email);
		if (null == user || !user.getPassword().equals(KeyUtils.md5Encrypt(password)))
		{
			params.put("error", "用户不存在或者密码错误");
		} else if (user.getStatus().equals(UserStatusEnum.DISABLE.ordinal()))
		{
			params.put("error", "用户已被禁止登录");
		} else
		{
			String ip = LoginUtils.getIpAddr(request);
			if (!user.getLastLoginIp().equals(ip))
			{
				HashMap<String, Object> map = new HashMap<>();
				map.put("userId", user.getUserId());
				map.put("ip", ip);
				AppEvent appEvent = new AppEvent(map, RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
				userOffsitePublisher.publish(appEvent);
			}
			userService.updateUserLastLoginTimeAndIp(user.getUserId(), ip);
			String token = UUID.randomUUID().toString();
			encryptedToken = RSAUtils.encryptByPrivate(token, keyProperties.getLoginPrivateKey());
			System.out.println(encryptedToken);
			CookieUtils.setUserToken(response, CookieConstant.USER_TOKEN, encryptedToken,
					CookieConstant.USER_TOKEN_EXPIRE);
			request.getSession().setAttribute(SessionConstant.USER_LOGIN_TOKEN_IN_SESSION, token);
			
			redisService.set(String.format(RedisConstant.USER_INFO, token), JsonUtils.obj2Json(user),
					RedisConstant.USER_INFO_EXPIRE);
		}
		if (params.containsKey("error"))
		{
			modelAndView = new ModelAndView("login", params);
		} else
		{
			String string = URLEncoder.encode(encryptedToken,"utf-8");
			System.out.println(string);
			request.getSession().setAttribute(SessionConstant.markIsAuth, true);
			modelAndView = new ModelAndView("redirect:" + redirectUrl + "token=" + string);
		}
		return modelAndView;
	}
}
