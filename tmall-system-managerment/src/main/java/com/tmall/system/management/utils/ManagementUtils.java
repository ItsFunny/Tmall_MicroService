/**
*
* @author joker 
* @date 创建时间：2018年6月22日 上午9:52:20
* 
*/
package com.tmall.system.management.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.tmall.common.constants.SessionConstant;
import com.tmall.common.dto.RoleDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.exception.TmallUserNotLoginException;

/**
* 
* @author joker 
* @date 创建时间：2018年6月22日 上午9:52:20
*/
public class ManagementUtils
{
	public static UserDTO getLoginUser()
	{
		
//		Subject subject = SecurityUtils.getSubject();
//		Object userObj = subject.getSession(true).getAttribute(SessionConstant.USER_IN_SESSION);
//		if(null==userObj)
//		{
//			throw new TmallUserNotLoginException("请先登录");
//		}
//		return (UserDTO)userObj;
		/*
		 * 因为只是测试,所以先直接伪造userDTO
		 */
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername("joker");
		userDTO.setUserId(1L);
		RoleDTO roleDTO=new RoleDTO();
		roleDTO.setRoleId(0);
		userDTO.setSelfRole(roleDTO);
		return userDTO;
	}
}
