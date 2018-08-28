/**
*
* @author joker 
* @date 创建时间：2018年6月9日 下午2:05:55
* 
*/
package com.tmall.system.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.constants.SessionConstant;
import com.tmall.common.constants.TmallURLConstant;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月9日 下午2:05:55
 */
@Controller
public class LoginController
{
//	@Autowired
//	private ILoginFeignService loginFeignService;
//	
//	@Autowired
//	private ITestService tesetService;
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = null;
		String redirectUrl = request.getParameter("redirectUrl");
		redirectUrl = StringUtils.isEmpty(redirectUrl) ? TmallURLConstant.TMALL_PORTAL_INDEX_URL : redirectUrl;
		modelAndView = new ModelAndView("login");
		modelAndView.addObject("redirectUrl", redirectUrl);
		return modelAndView;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		modelAndView=new ModelAndView("register");
		return modelAndView;
	}
//	@RequestMapping("/register.do")
//	public ModelAndView doRegister(@RequestBody FormUser formUser ,HttpServletRequest request,HttpServletResponse response)
//	{
//		
//		ModelAndView modelAndView=null;
//		HttpSession session = request.getSession(true);
//		/*
//		 * 设置注册的间隔时间
//		 */
//		Object lastRegisterDate = session.getAttribute(SessionConstant.USER_REGISTER);
//		
//		
//		
//		return modelAndView;
//	}

	@RequestMapping("/login.do")
	public ModelAndView doLogin(HttpServletRequest requestq, HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		
		
		return modelAndView;
	}
//	@RequestMapping("/cloud/test3")
//	public String test3()
//	{
//		return loginFeignService.test();
//	}
//	@RequestMapping("/cloud/test2")
//	public String test2()
//	{
//		return tesetService.test();
//	}
}
