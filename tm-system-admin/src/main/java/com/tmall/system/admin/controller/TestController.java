/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午4:42:51
* 
*/
package com.tmall.system.admin.controller;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.utils.JWTUtils;
import com.tmall.server.spi.store.IStoreServerFeignService;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月26日 下午4:42:51
 */
@Controller
public class TestController
{
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private IStoreServerFeignService storeServerFeginService;

	@Autowired
	private JWTUtils jwtUtils;
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("test");
		String from = request.getParameter("from");
		String test = request.getParameter("test");
		logger.error("[test] from:{},test:{}", from, test);
		return modelAndView;
	}

	@RequestMapping("/test1")
	public ModelAndView test1(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("test", "test1");
		params.put("testsss", "aaaa");
		ModelAndView modelAndView = new ModelAndView("redirect:/test2", params);
		return modelAndView;
	}

	@RequestMapping("/test2")
	public ModelAndView test2(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("test");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements())
		{
			System.out.println(headerNames.nextElement());
		}
		Collection<String> names = response.getHeaderNames();
		for (String string : names)
		{
			System.out.println(string);
		}
		return modelAndView;
	}
	
	@RequestMapping("/test4")
	public ModelAndView test4()
	{
		storeServerFeginService.test();
		return new ModelAndView("test");
	}
	
//	
//	@RequestMapping("/test3")
//	public ModelAndView test3(HttpServletRequest request,HttpServletResponse response)
//	{
//		throw new TmallBussinessException(TmallBussinessException.UNAUTHENTICATED_EXCEPTION,"ceshi");
//	}
	

}
