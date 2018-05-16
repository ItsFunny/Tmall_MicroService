/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午9:04:14
* 
*/
package org.login_server.controller;

import java.util.HashMap;
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
import org.login_server.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rebuildtmall.tmall_micro_common.enums.RabbitMQEnum;
import com.rebuildtmall.tmall_micro_common.event.AppEvent;
import com.rebuildtmall.tmall_micro_common.event.impl.UserOffsitePublisher;
import com.rebuildtmall.tmall_micro_common.utils.JsonUtils;
import com.rebuildtmall.tmall_micro_common.utils.KeyUtils;

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

	@Autowired
	private UserOffsitePublisher userOffsitePublisher;

	public static void main(String[] args)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", "12333");
		map.put("username", "joker");
		AppEvent appEvent = new AppEvent(map, "a");
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

	}

	@RequestMapping("/test")
	@ResponseBody
	public String test()
	{
		// User user = new User();
		// user.setIDCard("asdasd");
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", "1");
		map.put("ip", "121283asdj");
		AppEvent event = new AppEvent(map, RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
		userOffsitePublisher.publish(event);
		return "ok";
	}

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
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response)
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
			String ip = LoginUtils.getIpAddr(request);
//			if (!user.getLastLoginIp().equals(ip))
//			{
//				// 发布一个消息,这个消息的topic为user_abnormal_info,同时记录用户异常信息的日志(持久化到数据库中,然后数据库中会有一个后台schedual线程进行数据分析)
//				// AppEvent appEvent=new AppEvent(data, routingKey)
//				HashMap<String, Object> map = new HashMap<>();
//				map.put("userId", user.getUserId());
//				map.put("ip", ip);
//				AppEvent appEvent = new AppEvent(map, RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey());
//				userOffsitePublisher.publish(appEvent);
//			}
//			

			System.out.println(KeyUtils.md5Encrypt(password));
			if (null == user || !user.getPassword().equals(KeyUtils.md5Encrypt(password))
					|| user.getStatus().equals(UserStatusEnum.DISABLE.ordinal()))
			{
				modelAndView = new ModelAndView("login");
				modelAndView.addObject("error", "用户不存在或者密码错误或者用户已被禁止登陆");
				 return modelAndView;
			} else
			{
				userService.updateUserLastLoginTimeAndIp(user.getUserId(), ip);
				String t = UUID.randomUUID().toString();
				CookieUtils.setUserToken(response, CookieConstant.USER_TOKEN, t, CookieConstant.USER_TOKEN_EXPIRE);
				redisService.set(String.format(RedisConstant.USER_INFO, t), JsonUtils.obj2Json(user));

				// 跳转,带上token
			}
		}
		return null;
	}

}
