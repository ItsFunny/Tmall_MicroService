/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午11:19:29
* 
*/
package org.login_server.controller;

import javax.servlet.http.HttpServletRequest;

import org.login_server.test.Test1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* 
* @author joker 
* @date 创建时间：2018年5月10日 上午11:19:29
*/
@Controller
public class TestController
{
	@RequestMapping("/test/{key}")
	public void test(HttpServletRequest request,@PathVariable("key")Integer key)
	{
		Test1.put(key, request.getSession(true));
	}
	
	@RequestMapping("/ban/{key}")
	public void banTest(HttpServletRequest request,@PathVariable("key")Integer key)
	{
		Test1.ban(key);
	}
	
	

}
