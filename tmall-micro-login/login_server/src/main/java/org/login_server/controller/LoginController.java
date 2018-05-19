/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午9:04:14
* 
*/
package org.login_server.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.constants.CookieConstant;
import com.tmall.common.constants.RedisConstant;
import com.tmall.common.constants.SecurityConstant;
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
	@RequestMapping("/verify")
	public boolean verifySession(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = null;
		String serverName = request.getParameter("serverName");
		boolean isAuth = (boolean) request.getSession(true).getAttribute(SessionConstant.markIsAuth);
		if (isAuth)
		{
			// 说明已经登录了
			// 然后将请求过来的地址将其加入此次session的白名单
			// 2018-05-17 20:22 感觉没必要,我直接将模块名加入白名单即可
			// String clientToken = CookieUtils.getUserToken(request,
			// CookieConstant.USER_TOKEN);
			// if(StringUtils.isEmpty(clientToken))
			// {
			// logger.error("没有获取到相应的token");
			// }

			return true;
		} else
		{
			return false;
		}

	}

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

	// @RequestMapping("/checkLogin")
	// @ResponseBody
	// public boolean checkLogin(HttpServletRequest request, HttpServletResponse
	// response)
	// {
	// String token = request.getParameter("token");
	// String s = request.getHeader(SecurityConstant.HEADER_SIGN);
	// System.out.println(s);
	//
	// // 第一重验证,
	// Object isAuth =
	// request.getSession(true).getAttribute(SessionConstant.markIsAuth);
	// // 如果是第二个子系统,第一次访问的时候是会为null的,则会跳转到认证中心认证是否登录
	// if (null == isAuth || !(boolean) isAuth)
	// {
	// // 校验用户是否真的登录
	// // 第二重验证,这重验证是基于cookie的,与当前浏览器保存着的cookie进行匹配(当然这个cookie是全局的,天猫使用的就是全局的cookie)
	// // 第三重验证,验证是否过期,redis中登录成功后会保存用户信息
	// // && (null != CookieUtils.getUserToken(request, CookieConstant.USER_TOKEN)
	// // && CookieUtils.getUserToken(request,
	// // CookieConstant.USER_TOKEN).equals(token))
	// if (!StringUtils.isEmpty(token)
	// &&
	// !StringUtils.isEmpty(redisService.get(String.format(RedisConstant.USER_INFO,
	// token))))
	// {
	// return true;
	// }
	// return false;
	// } else
	// {
	// return true;
	// }
	// // if (StringUtils.isEmpty(token)
	// // ||
	// //
	// StringUtils.isEmpty(redisService.get(String.format(RedisConstant.USER_INFO,
	// // token))))
	// // {
	// //// modelAndView = new ModelAndView(
	// //// "redirect:" + TmallURLConstant.TMALL_LOGIN_URL + "?redirectUrl=" +
	// // redirectUrl);
	// // return false;
	// // } else
	// // {
	// //// modelAndView = new ModelAndView("redirect:" + redirectUrl);
	// // return true;
	// // }
	// // return modelAndView;
	// }

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		String redirectUrl = request.getParameter("redirectUrl");
		redirectUrl = StringUtils.isEmpty(redirectUrl) ? TmallURLConstant.TMALAL_PORTAL_INDEX_URL : redirectUrl;
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("redirectUrl", redirectUrl);
		return modelAndView;
	}

	/*
	 * 这个方法会跳转到portal 服务,这里的话先直接返回null
	 */
	// @RequestMapping("/login.do")
	// public @ResponseBody ModelAndView doLogin(HttpServletRequest request,
	// HttpServletResponse response)
	// {
	// ModelAndView modelAndView = null;
	// String token = CookieUtils.getUserToken(request, CookieConstant.USER_TOKEN);
	// String redirectUrl = request.getParameter("redirectUrl");
	// System.out.println(redirectUrl);
	// if (!StringUtils.isEmpty(token)
	// &&
	// !StringUtils.isEmpty(redisService.get(String.format(RedisConstant.USER_INFO,
	// token))))
	// {
	// modelAndView = new ModelAndView("redirect:" + redirectUrl + "?token=" +
	// token);
	// } else
	// {
	// String email = request.getParameter("email");
	// String password = request.getParameter("password");
	// User user = userService.findByEmail(email);
	// String ip = LoginUtils.getIpAddr(request);
	// if (null == user || !user.getPassword().equals(KeyUtils.md5Encrypt(password))
	// || user.getStatus().equals(UserStatusEnum.DISABLE.ordinal()))
	// {
	// modelAndView = new ModelAndView("login");
	// modelAndView.addObject("error", "用户不存在或者密码错误或者用户已被禁止登陆");
	// return modelAndView;
	// } else
	// {
	// if (!user.getLastLoginIp().equals(ip))
	// {
	// //
	// 发布一个消息,这个消息的topic为user_abnormal_info,同时记录用户异常信息的日志(持久化到数据库中,然后数据库中会有一个后台schedual线程进行数据分析)
	// // AppEvent appEvent=new AppEvent(data, routingKey)
	// HashMap<String, Object> map = new HashMap<>();
	// map.put("userId", user.getUserId());
	// map.put("ip", ip);
	// AppEvent appEvent = new AppEvent(map,
	// RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
	// userOffsitePublisher.publish(appEvent);
	// }
	// HttpSession session = request.getSession(true);
	// userService.updateUserLastLoginTimeAndIp(user.getUserId(), ip);
	// String t = UUID.randomUUID().toString();
	//// String encryptString = RSAUtils.encryptByPrivate(t, new
	// String(keyProperties.getLoginPublicKey()));
	// session.setAttribute(SessionConstant.markIsAuth, true);
	// CookieUtils.setUserToken(response, CookieConstant.USER_TOKEN, t,
	// CookieConstant.USER_TOKEN_EXPIRE);
	// redisService.set(String.format(RedisConstant.USER_INFO, t),
	// JsonUtils.obj2Json(user));
	// // 跳转,带上token
	// //在头上加上签名
	// System.out.println(keyProperties.getLoginPrivateKey());
	// System.out.println(new String(keyProperties.getLoginPrivateKey()));
	// String sign=RSAUtils.sign(SecurityConstant.LOGIN_LOGO,
	// keyProperties.getLoginPrivateKey());
	//
	// response.addHeader(SecurityConstant.HEADER_SIGN, sign);
	// response.addHeader(SecurityConstant.HEADER_PRIMITIVE,
	// SecurityConstant.LOGIN_LOGO);
	// modelAndView = new ModelAndView("redirect:" + redirectUrl + "?token=" +
	// t+"&sign="+sign.getBytes()+"&primitive="+SecurityConstant.LOGIN_LOGO);
	// }
	// }
	// return modelAndView;
	// }
	// function: 校验token有效性,以及认证是否登录
	@RequestMapping("/authToken")
	public void authToken( String token, HttpServletRequest request,
			HttpServletResponse response) 
	{

		PrintWriter writer = null;
		try
		{
			token=URLDecoder.decode(token, "utf-8");
			writer = response.getWriter();
//			if (result.hasErrors())
//			{
//				writer.write("");
//			}
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
		String redirectUrl = StringUtils.isEmpty(request.getParameter("redirectUrl"))
				? TmallURLConstant.TMALAL_PORTAL_INDEX_URL
				: request.getParameter("redirectUrl");
		String encryptedToken = CookieUtils.getUserToken(request, CookieConstant.USER_TOKEN);
		if (!StringUtils.isEmpty(encryptedToken))
		{
			String primitiveToken = RSAUtils.decryptByPublic(encryptedToken, keyProperties.getLoginPublicKey());
			if (!StringUtils.isEmpty(primitiveToken)
					&& !StringUtils.isEmpty(redisService.get(String.format(RedisConstant.USER_INFO, primitiveToken))))
			{
				request.getSession(true).setAttribute(SessionConstant.markIsAuth, true);
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
			request.getSession(true).setAttribute(SessionConstant.markIsAuth, true);
			modelAndView = new ModelAndView("redirect:" + redirectUrl + "token=" + string);
		}
		return modelAndView;
	}

	// public static void main(String[] args)
	// {
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// map.put("userId", "12333");
	// map.put("username", "joker");
	// AppEvent appEvent = new AppEvent(map, "a");
	// String string = JSONObject.fromObject(appEvent).toString();
	// System.out.println(string);
	// JSONObject object = JSONObject.fromObject(string);
	// Object bean = JSONObject.toBean(object,appEvent.getClass());
	// System.out.println(bean);

	// GsonBuilder gsonBuilder=new GsonBuilder();
	// Gson gson = gsonBuilder.registerTypeAdapter(Serializable.class, new
	// GsonInterfaceAdapter<Serializable>()).create();
	// // Gson gson=new Gson();
	// String json = gson.toJson(appEvent);
	// System.out.println(json);
	// AppEvent appEvent2 = gson.fromJson(json, AppEvent.class);
	// System.out.println(appEvent2);

	// User user = new User();
	// user.setIDCard("1233");
	// AppEvent appEvent3 = new AppEvent(user, "a");
	// GsonBuilder gsonBuilder = new GsonBuilder();
	// Gson gson = gsonBuilder.registerTypeAdapter(Serializable.class, new
	// GsonInterfaceAdapter<Serializable>())
	// .create();
	// AppEvent a = gson.fromJson(gson.toJson(appEvent3), AppEvent.class);
	// System.out.println(a);
	// GsonBuilder gsonBuilder = new GsonBuilder();
	// gsonBuilder.registerTypeAdapter(Serializable.class, new
	// GsonInterfaceAdapter<Serializable>());
	// Gson gson = gsonBuilder.create();
	// String json = gson.toJson(appEvent);
	// AppEvent appEvent2 = gson.fromJson(json,new TypeToken<AppEvent>);
	// System.out.println(appEvent2);
	// }

}
