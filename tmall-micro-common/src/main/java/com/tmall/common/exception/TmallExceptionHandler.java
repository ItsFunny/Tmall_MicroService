package com.tmall.common.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.joker.library.dto.ResultDTO;
import com.joker.library.utils.CommonUtils;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.utils.ResultUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@EnableConfigurationProperties(TmallConfigProperty.class)
public class TmallExceptionHandler
{


	@Autowired
	private LoadBalancerClient loadbalancerClient;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	private String getServerUrl(String server)
	{
		ServiceInstance instance = loadbalancerClient.choose(server);
		String host = instance.getHost();
		int port = instance.getPort();
		// 这个http不能这样写,感觉
		return "http://" + host + ":" + port;
	}
	
	// 处理语法逻辑异常
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResultDTO<String> handlerIllegalArgument(IllegalArgumentException exception)
	{
		log.error("[handlerIllegalArgument]检测到违法参数:{}", exception.getMessage(), exception);
		return ResultUtils.fail("违法的参数:" + exception.getMessage());
	}
	// 处理业务异常
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(TmallBussinessException.class)
	public ResultDTO<String> handlerBizException(TmallBussinessException exception)
	{
		log.error("[handlerBizException]业务逻辑出现异常:{}", exception.getMessage(), exception);
		return ResultUtils.fail(exception.getMessage());
	}
	/*
	 * 处理需要跳转的异常,如用户未登录等
	 */
	@ExceptionHandler(TmallUserException.class)
	public ModelAndView hanlderUserException(TmallUserException userException)
	{
		ModelAndView modelAndView = null;
		if (Integer.valueOf(userException.getCode()).equals(ErrorCodeEnum.USER_NOT_EXIST_2001.getCode()))
		{
			modelAndView = new ModelAndView("redirect:" + getServerUrl("SSO-SYSTEM"));
			modelAndView.addObject("redirectUrl", CommonUtils.getRedirectUrl());
			modelAndView.addObject("storeAbbName", tmallConfigProperty.getApplicationName());
		} else
		{
			modelAndView = new ModelAndView("error");
			modelAndView.addObject("error", userException.getMessage());
		}
		return modelAndView;
	}
	/*
	 * 处理全局异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResultDTO<String> handlerAllException(Exception exception)
	{
		log.error("[handlerAllException]处理全局异常,异常信息:{}", exception.getMessage(), exception);
		return ResultUtils.fail(exception.getMessage());
	}
}
