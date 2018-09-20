/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:31:47
* 
*/
package com.tmall.system.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 上午10:31:47
*/
@Controller
@RequestMapping(value="/admin/property")
public class PropertyController
{
	//这里需要再添加权限
	@RequestMapping(value="/show")
	public ModelAndView showAllSPU(HttpServletRequest request,HttpServletResponse response)
	{
		Map<String, Object>params=new HashMap<String, Object>();
		ModelAndView modelAndView=null;
		//分页查询所有的规格选项
		//从有到无,因此先从添加开始做起
		modelAndView=new ModelAndView("properties",params);
		return modelAndView;
	}

}
