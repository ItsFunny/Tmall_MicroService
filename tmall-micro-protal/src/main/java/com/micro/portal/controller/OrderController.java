/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午7:54:00
* 
*/
package com.micro.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午7:54:00
 */
@Controller
@RequestMapping("/order")
public class OrderController
{
	@RequestMapping("/test")
	@ResponseBody
	public String  test(HttpServletRequest request)
	{
		System.out.println(request.getSession(true).getId());
		return "test";
	}
	@RequestMapping("/session/test1")
	public ModelAndView testSession(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		System.out.println(request.getSession(true).getId());
		modelAndView=new ModelAndView("redirect:/order/session/test2");	
		return modelAndView;
	}
	@RequestMapping("/session/test2")
	public ModelAndView testSession2(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		System.out.println(request.getSession(true).getId());
//		modelAndView=new ModelAndView("");	
		return modelAndView;
	}
	/*
	 * 请求转发
	 */
	@RequestMapping("/session/test3")
	public ModelAndView testSession3(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		System.out.println(request.getSession(true).getId());
		return modelAndView;
	}
	@RequestMapping("/test2")
	@ResponseBody
	public String test2()
	{
		return "test2";
	}
}
