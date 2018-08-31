/**
*
* @author joker 
* @date 创建时间：2018年8月16日 下午5:19:26
* 
*/
package com.tmall.system.admin.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;

import com.tmall.common.dto.UserDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月16日 下午5:19:26
*/
public class AdminUtil
{
	public static UserDTO getLoginUser()
	{
		Subject subject = SecurityUtils.getSubject();
		if(null!=subject)
		{
			return (UserDTO) subject.getPrincipal();
		}else {
			//交由异常处理器统一处理
			throw new UnauthenticatedException("用户未登录");
		}
	}
}
