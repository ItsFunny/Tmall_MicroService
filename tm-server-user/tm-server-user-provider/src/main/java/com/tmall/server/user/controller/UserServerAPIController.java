/**
*
* @author joker 
* @date 创建时间：2018年6月14日 上午8:26:33
* 
*/
package com.tmall.server.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.reflect.TypeToken;
import com.joker.library.utils.JsonUtil;
import com.joker.library.utils.KeyUtils;
import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.enums.UserStatusEmun;
import com.tmall.common.utils.JWTUtils;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.user.service.ILoginHandler;
import com.tmall.server.user.service.IUserService;
import com.tmall.server.user.common.exception.TmallUserException;
import com.tmall.server.user.common.model.TmallUser;
import com.tmall.server.user.service.AbstractLoginHandler.FormUser;

/**
 *
 * 需要本人才能访问
 * 
 * @author joker
 * @date 创建时间：2018年6月14日 上午8:26:33
 */
@RestController
public class UserServerAPIController
{
	private Logger logger=LoggerFactory.getLogger(UserServerAPIController.class);
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginHandler loginHandler;

	@Autowired
	private JWTUtils jwtUtil;

	@RequestMapping(value = "/login",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<UserDTO> login(@RequestBody String loginToken)
	{
		Map<String, Object> authentications = new HashMap<>();
		try
		{
			String json = jwtUtil.parseBySSOPublicKeyReturnJson(loginToken);
			if (StringUtils.isEmpty(json))
			{
				return ResultUtils.fail("无效的登录参数认证信息:token");
			}
			authentications=JsonUtil.json2Map(json, new TypeToken<Map<String, Object>>()
			{
			}.getType());
		} catch (Exception e)
		{
			return ResultUtils.fail("错误的登录参数认证信息:token");
		}
		if(null==authentications)
		{
			return ResultUtils.fail("无效的登录参数认证信息:token");
		}
		Object time=authentications.get(AuthConstant.INVALID_TIME);
		if(null==time || System.currentTimeMillis()>Long.parseLong(time.toString()))
		{
			return ResultUtils.fail("身份信息已过期,请重新输入");
		}
		Object loginKey=authentications.get("loginKey");
		Object password=authentications.get("password");
		if(null==loginKey||password==null)
		{
			return ResultUtils.fail("认证信息账户和密码不能为空");
		}
		FormUser formUser=new FormUser();
		formUser.setLoginKey(loginKey.toString());
		formUser.setPassword(password.toString());
		UserDTO userDTO=null;
		try
		{
			formUser.parse();
			userDTO=loginHandler.findUser(formUser);
			String error=null;
//			if (null == userDTO || !userDTO.getPassword().equals(KeyUtils.md5Encrypt(formUser.getPassword())))
			if (null == userDTO || !userDTO.getPassword().equals(formUser.getPassword()))
			{
				error= "账户不存在,或者密码错误";
			} else if (userDTO.getStatus().equals(UserStatusEmun.disable.ordinal()))
			{
				error="用户已被禁止登录";
			}
			if (!StringUtils.isEmpty(error))
			{
				return ResultUtils.fail(error);
			}
		} catch (Exception e)
		{
			return ResultUtils.fail(e.getMessage());
		}
		return ResultUtils.sucess(userDTO);
	}

	@RequestMapping(value = "/updateUser")
	public ResultDTO<Integer> updateUserInfo(@RequestBody UserDTO userDTO)
	{
		try
		{
			userService.updateUser(userDTO);
			/*
			 * when update sucess ,should reload the userinfo by send rabbitmq message to
			 * reload remain to do ~~~~
			 */
			/*
			 * 1.从redis 中获取session 2.发布消息
			 * 3.各个系统端消费消息(从event中获取session,然后invadilate),是在系统端配置,不需要再服务端配置,这个消息不需要放到batch中
			 */
			HashMap<String, Object> data = new HashMap<>();
			return ResultUtils.sucess();
		} catch (Exception e)
		{
			e.printStackTrace();
			return ResultUtils.fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/changePwd")
	public ResultDTO<?> changePwd(HttpServletRequest request)
	{
		// 需要进行加密解密吗,还是说直接将service这个依赖导入到其他依赖中
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String userIdStr = request.getParameter("userId");
		if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(userIdStr))
		{
			throw new IllegalArgumentException("sorry,need detail param");
		}
		Long userId = Long.parseLong(userIdStr);
		boolean flag = userService.updateUserPassword(newPwd, oldPwd, userId);
		if (flag)
		{
			return ResultUtils.sucess("change password,sucessfully and u need to relogin ");
		} else
		{
			return ResultUtils.fail("sorry, check your previous password");
		}
		// request.getParameter("oldP")
	}
	@RequestMapping(value="/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<UserDTO>findByUserId(@PathVariable("userId")Long userId)
	{
		TmallUser user = userService.findUserByUserId(userId);
		if(null==user)
		{
			throw new TmallUserException(ErrorCodeEnum.USER_NOT_EXIST_2001);
		}
		UserDTO userDTO=new UserDTO();
		user.to(userDTO);
		return ResultUtils.sucess(userDTO);
	}
}
