/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午2:37:59
* 
*/
package com.tmall.system.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* 
* @author joker 
* @date 创建时间：2018年9月2日 下午2:37:59
*/

@Controller
public class OpenController
{
	//处理无权访问页面
	@RequestMapping("/noauth")
	public ModelAndView noAuth()
	{
		return new ModelAndView("noauth");
	}

}
