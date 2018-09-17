package com.tmall.system.sso.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joker.library.dto.ResultDTO;
import com.joker.library.utils.JsonUtil;
import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.server.spi.auth.IAuthFeignService;
import com.tmall.server.spi.user.IUserFeignService;
import com.tmall.system.sso.model.FormUserModel;

@Controller
public class SSOController
{
	@Autowired
	private IUserFeignService userFeignService;

	@Autowired
	private IAuthFeignService authFeignService;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("login");
		String redirectUrl = request.getParameter("redirectUrl");
		String storeAbbName = request.getParameter("storeAbbName");
		modelAndView.addObject("redirectUrl", redirectUrl);
		modelAndView.addObject("storeAbbName", storeAbbName);
		return modelAndView;
	}

	@RequestMapping(value = "/doLogin")
	public ModelAndView doLogin(FormUserModel userModel, @Valid BindingResult result,
			@RequestParam Map<String, Object> params, HttpServletRequest request)
	{

		ModelAndView modelAndView = null;
		if (result.hasErrors())
		{
			String error = "";
			for (ObjectError error2 : result.getAllErrors())
			{
				error += error2.getDefaultMessage();
			}
			params.put("error", error);
			modelAndView = new ModelAndView("login", params);
			return modelAndView;
		}
		userModel.setInvalidTime(System.currentTimeMillis() + 1000 * 60 * 3);
		/*
		 * 调用user提供的接口
		 */
		String json = JsonUtil.obj2Json(userModel);
		ResultDTO<UserDTO> userRes = userFeignService.login(json);
		if (!userRes.isSuccess())
		{
			params.put("error", userRes.getMsg());
			modelAndView = new ModelAndView("login", params);
			return modelAndView;
		}
		/*
		 * 调用auth接口,获取基本信息
		 */
		String storeAbbName = params.get("storeAbbName").toString();
		AuthTokenDTO tokenDTO = new AuthTokenDTO();
		tokenDTO.setData(userRes.getData());
		tokenDTO.setInvalidTime(System.currentTimeMillis() + 1000 * 60 * 5);
		tokenDTO.setStoreAbbName(storeAbbName);
		String authJson = JsonUtil.obj2Json(tokenDTO);
		ResultDTO<String> authRes = authFeignService.auth(authJson);
		if (!authRes.isSuccess())
		{
			params.put("error", authRes.getMsg());
			modelAndView = new ModelAndView("error", params);
			return modelAndView;
		}
		String token = authRes.getData();
		String redirectUrl = params.get("redirectUrl").toString();
		modelAndView = new ModelAndView("redirect:" + redirectUrl);
		modelAndView.addObject("token", token);
		request.getSession(true).setAttribute(AuthConstant.AUTH_IN_SESSION, token);
		return modelAndView;
	}
}
