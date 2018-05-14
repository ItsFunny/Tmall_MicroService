/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午9:04:14
* 
*/
package org.login_server.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login_server.constants.CookieConstant;
import org.login_server.constants.RedisConstant;
import org.login_server.enums.UserStatusEnum;
import org.login_server.model.User;
import org.login_server.redis.IRedisService;
import org.login_server.service.IUserService;
import org.login_server.utils.CookieUtils;
import org.login_server.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rebuildtmall.tmall_micro_common.utils.JsonUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月10日 上午9:04:14
 */
@Controller
public class LoginController
{
	@Autowired
	private IRedisService redisService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	/*
	 * 这个方法会跳转到portal 服务,这里的话先直接返回null
	 */
	@RequestMapping("/login.do")
	public String doLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String token = CookieUtils.getUserToken(request, CookieConstant.USER_TOKEN);
		ModelAndView modelAndView = null;
		if (!StringUtils.isEmpty(token))
		{
			// 这里就可以直接跳转到protal 服务了,那里会提供一个api
			// String json = redisService.get(token);
			// if(!StringUtils.isEmpty(json))
			// {
			// User user = JsonUtils.json2Object(json, User.class);
			// }
		} else
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			User user = userService.findByEmail(email);
			System.out.println(KeyUtils.md5Encrypt(password));
			if (null == user || user.getStatus().equals(UserStatusEnum.DISABLE.ordinal())
					||!user.getPassword().equals(KeyUtils.md5Encrypt(password)))
			{
				modelAndView = new ModelAndView("login");
				modelAndView.addObject("error", "用户不存在或者密码错误或者用户已被禁止登陆");
//				return modelAndView;
				return "error";
			} else
			{
				String t = UUID.randomUUID().toString();
				CookieUtils.setUserToken(response, CookieConstant.USER_TOKEN, t, CookieConstant.USER_TOKEN_EXPIRE);
				redisService.set(String.format(RedisConstant.USER_INFO, t), JsonUtils.obj2Json(user));
				//跳转,带上token
			}

		}
		return null;
	}

}
