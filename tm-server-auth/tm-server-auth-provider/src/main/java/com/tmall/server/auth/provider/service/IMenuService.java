/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午11:37:38
* 
*/
package com.tmall.server.auth.provider.service;

import java.util.List;

import com.tmall.common.dto.MenuDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月21日 下午11:37:38
*/
public interface IMenuService
{
	List<MenuDTO>findMenusByRoleId(Long roleId);
}
