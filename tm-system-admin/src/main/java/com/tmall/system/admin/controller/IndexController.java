/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午8:19:23
* 
*/
package com.tmall.system.admin.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.dto.UserDTO;
import com.tmall.system.admin.handler.AdminHelper;
import com.tmall.system.admin.util.AdminUtil;
import com.tmall.system.admin.vo.MenuVO;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月26日 下午8:19:23
 */
@Controller
@RequestMapping("/admin")
public class IndexController
{
	@Autowired
	private AdminHelper menuHandler;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, Object>params=new HashMap<String, Object>();
		
		UserDTO user = AdminUtil.getLoginUser();
		Collection<MenuVO> menuVOs = menuHandler.parseUserMenus(user);
		params.put("menus", menuVOs);
		
		if(user.isSuperAdmin())
		{
			//显示具体的总结信息
		}
		ModelAndView modelAndView = new ModelAndView("indexPage",params);
		return modelAndView;
	}

	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("home");
		
		
		return modelAndView;
	}
}
