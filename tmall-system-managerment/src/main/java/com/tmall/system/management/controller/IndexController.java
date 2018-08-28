/**
*
* @author joker 
* @date 创建时间：2018年8月10日 下午8:04:11
* 
*/
package com.tmall.system.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* 
* @author joker 
* @date 创建时间：2018年8月10日 下午8:04:11
*/
@RequestMapping("/admin")
public class IndexController
{
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		
		return modelAndView;
	}

}
