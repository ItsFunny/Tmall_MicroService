/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午2:43:52
* 
*/
package com.tmall.system.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* 
* @author joker 
* @date 创建时间：2018年8月21日 下午2:43:52
*/
@RequestMapping("/admin/brand")
public class BrandController
{	
	
	@RequestMapping("/all")
	public ModelAndView showAllBrands(HttpServletRequest request,HttpServletResponse response)
	{
		
		
		ModelAndView modelAndView=new ModelAndView("brands");
		
		
		return modelAndView;
	}

}
