/**
*
* @author joker 
* @date 创建时间：2018年6月22日 上午10:14:09
* 
*/
package com.tmall.system.management.exhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.joker.library.utils.UrlUtils;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.exception.TmallUserNotLoginException;

/**
* 
* @author joker 
* @date 创建时间：2018年6月22日 上午10:14:09
*/
@ControllerAdvice
public class ManagementExceptionHandler
{
	@ExceptionHandler(value=TmallUserNotLoginException.class)
	public ModelAndView handlerNotLoginException(Exception exception,HttpServletRequest request)
	{
		ModelAndView modelAndView=null;
		String redirectUrl = UrlUtils.getRedirectUrl(request);
		String loginUrl=TmallURLConstant.TMALL_LOGIN_URL+"?redirectUrl="+redirectUrl;
		modelAndView=new ModelAndView("redirect:"+loginUrl);
		return modelAndView;
	}
	
}
