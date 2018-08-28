/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午7:58:47
* 
*/
package com.tmall.server.auth.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallRole;


/**
 * 
 * @author joker
 * @date 创建时间：2018年8月2日 下午7:58:47
 */
@Mapper
public interface RoleDao extends TmallRoleDao
{
	// String FIND_ROLES_BY_USERID_AND_STOREID = "select
	// role_id,store_id,role_status,roel_name,role_description from tmall_role"
	// + " where role_id in (select role_id from tmall_user_role where
	// user_id=#{userId}) and store_id=#{storeId} ";

	// @Select(FIND_ROLES_BY_USERID_AND_STOREID)
	Collection<TmallRole> findUserRoleByUserIdAndStoreId(@Param("userId") long userId,
			@Param("storeId") Long storeId);
}
