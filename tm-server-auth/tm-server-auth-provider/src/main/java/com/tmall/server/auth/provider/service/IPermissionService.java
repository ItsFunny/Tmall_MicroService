/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午8:10:34
* 
*/
package com.tmall.server.auth.provider.service;

import java.util.List;

import com.tmall.common.dto.ActionDTO;

/**
* 权限相关的接口
* @author joker 
* @date 创建时间：2018年8月2日 下午8:10:34
*/
public interface IPermissionService
{
//	List<PermissionDTO> findPermissionsByRoleId(Integer roleId,Integer status);
	
//	List<TmallPermission>findPermissionsByRoleIdList(List<Integer> roleIdList,Integer status);
	
	
	List<ActionDTO>findRolePermissionByRoleId(Long roleId,Integer status);
}
