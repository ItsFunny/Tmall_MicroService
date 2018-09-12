/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午7:35:21
* 
*/
package com.tmall.server.auth.provider.service;

import com.tmall.server.auth.common.model.TmallRole;

/**
* 
* @author joker 
* @date 创建时间：2018年8月2日 下午7:35:21
*/
public interface IRoleService
{
	//查询用户在某个店铺下的具体角色,一个店铺一个用户只有1个角色
    TmallRole findRoleByUserIdAndStioreId(Long userId,Long storeId);
    
//    TmallUserRole findByUserIdAndStoreId(Long userId,Integer storeId);
}
