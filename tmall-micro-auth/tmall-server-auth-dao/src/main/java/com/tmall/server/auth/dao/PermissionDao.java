/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午8:16:25
* 
*/
package com.tmall.server.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.common.dto.ActionDTO;
import com.tmall.common.dto.PermissionDTO;
import com.tmall.server.auth.common.model.TmallAction;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月2日 下午8:16:25
 */
@Mapper
public interface PermissionDao extends TmallActionDao
{
	// String FIND_PERMISSIONS = "select
	// permission,permission_status,permission_name,resource_name,resource_type,resource_url
	// from tmall_permission"
	// + " where permission_id in (select permission_id from tmall_role_permission
	// where role_id=#{roleId}) and permission_status=#{permissionStatus}";

//	List<PermissionDTO> findRolePermissionsBySingleRwoleId(@Param("roleId") Integer roleId, @Param("permissionStatus") Integer status);

	//这个方法用于内部后台管理人员全部权限时候用到,基本上一个店铺对应一个角色,不会有多个角色
//	List<TmallPermission> findRolePermissionsByRoleIdList(@Param("ids") List<Integer> roelIdList,
//			@Param("permissionStatus") Integer status);
	
	
	
	
	
	
	
	
	
}
