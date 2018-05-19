/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午7:54:00
* 
*/
package com.micro.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String  test()
	{
		return "test";
	}
	@RequestMapping("/test2")
	@ResponseBody
	public String test2()
	{
		return "test2";
	}
}
