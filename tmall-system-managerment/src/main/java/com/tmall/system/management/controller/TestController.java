/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午2:56:47
* 
*/
package com.tmall.system.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

/**
* 
* @author joker 
* @date 创建时间：2018年6月21日 下午2:56:47
*/
@Controller
public class TestController
{
	
	@RequestMapping("/index")
	public ModelAndView test()
	{
		return new ModelAndView("index2");
	}
	@RequestMapping("/home")
	public ModelAndView home()
	{
		return new ModelAndView("home");
	}
	
//	@RequestMapping("/test")
//	@ResponseBody
//	public String test2()
//	{
//		return "test";
//	}
	
}
