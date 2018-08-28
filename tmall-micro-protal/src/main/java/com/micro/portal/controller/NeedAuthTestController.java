/**
*
* @author joker 
* @date 创建时间：2018年5月24日 下午3:23:45
* 
*/
package com.micro.portal.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月24日 下午3:23:45
 */
@Controller
public class NeedAuthTestController
{
	@RequiresRoles(value =
	{ "role1" })
	@RequestMapping("/test/1")
	@ResponseBody
	public String test1()
	{
		return "ok u got the role";
	}

	@RequiresPermissions(value =
	{ "permi1" })
	@RequestMapping("/test/permission")
	@ResponseBody
	public String testPermisison()
	{
		return "OK u got the permission";
	}

}
