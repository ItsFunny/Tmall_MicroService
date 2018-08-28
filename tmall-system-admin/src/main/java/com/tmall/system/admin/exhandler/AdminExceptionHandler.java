/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午4:58:03
* 
*/
package com.tmall.system.admin.exhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.exception.TmallUserException;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月18日 下午4:58:03
 */
@ControllerAdvice
public class AdminExceptionHandler
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

	@ExceptionHandler(value = TmallUserException.class)
	public ModelAndView handlerUserException(TmallUserException exception)
	{
		String serverUrl = getServerUrl("SSO-SYSTEM	")+"/login";
		ModelAndView modelAndView = new ModelAndView("redirect:" + serverUrl);
		modelAndView.addObject("storeAbbName", tmallConfigProperty.getApplicationName());
		return modelAndView;
	}

	@ExceptionHandler(TmallBussinessException.class)
	public ModelAndView hAndView(TmallBussinessException exception)
	{
		// logic 这样写问题很大哦..
		String errorMsg = exception.getMessage();
		ModelAndView modelAndView = new ModelAndView("test");
		switch (exception.getCode())
		{
		case TmallBussinessException.BIZ_EXCEPTION:
			break;
		case TmallBussinessException.FORBIDDEN_EXCEPTION:
		{
			String serverUrl = getServerUrl("sso");
			modelAndView = new ModelAndView("redirect:" + serverUrl);
			modelAndView.addObject("storeAbbName", tmallConfigProperty.getApplicationName());
		}
			break;
		case TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION:
			break;
		case TmallBussinessException.MISSING_ARGUMENT_EXCEPTION:
			break;
		case TmallBussinessException.TIMEOUT_EXCEPTION:
			break;
		case TmallBussinessException.UNAUTHENTICATED_EXCEPTION:
		{
			String serverUrl = getServerUrl("sso");
			modelAndView = new ModelAndView("redirect:" + serverUrl);
			modelAndView.addObject("storeAbbName", tmallConfigProperty.getApplicationName());
		}
			break;
		case TmallBussinessException.UNKNOWN_EXCEPTION:
			break;
		default:
			modelAndView = new ModelAndView("error");
			break;
		}
		modelAndView.addObject("error", errorMsg);
		return modelAndView;
	}

}
