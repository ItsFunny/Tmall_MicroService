/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午7:45:40
* 
*/
package com.tmall.server.auth.provider.service.impl;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.server.auth.common.excecption.AuthBizException;
import com.tmall.server.auth.common.model.TmallRole;
import com.tmall.server.auth.provider.dao.RoleDao;
import com.tmall.server.auth.provider.dao.TmallUserRoleDao;
import com.tmall.server.auth.provider.service.IRoleService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月2日 下午7:45:40
 */
@Service
public class RoleServiceImpl implements IRoleService
{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private TmallUserRoleDao userRoleDao;

	@Override
	public TmallRole findRoleByUserIdAndStioreId(Long userId, Long storeId)
	{
		
		
//		Collection<TmallRole> roles = roleDao.findUserRoleByUserIdAndStoreId(userId, storeId);
//		TmallRoleExample example=new TmallRoleExample();
//		Criteria criteria = example.createCriteria();
		Collection<TmallRole> roles = roleDao.findUserRoleByUserIdAndStoreId(userId, storeId);
		if (roles == null||roles.isEmpty())
		{
			return null;
		} else if (roles.size() == 1)
		{
			return roles.iterator().next();
		} else
		{
			throw new AuthBizException(ErrorCodeEnum.AUTH_MULTIROLES_1001);
		}
	}

	//
//	@Override
//	public TmallUserRole findByUserIdAndStoreId(Long userId, Integer storeId)
//	{
//		TmallUserRoleExample example=new TmallUserRoleExample();
//		com.tmall.server.auth.common.model.TmallUserRoleExample.Criteria criteria = example.createCriteria();
//		criteria.andUserIdEqualTo(userId);
//		criteria.andStoreIdEqualTo(storeId);
//		List<TmallUserRole> list = userRoleDao.selectByExample(example);
//		//一个用户一个商铺只能有1个角色
//		if(list==null||list.isEmpty())
//		{
//			return null;
//		}else if(list.size()==1)
//		{
//			return list.iterator().next();
//		}else {
//			throw new AuthBizException(ErrorCodeEnum.AUTH1001);
//		}
//	}


}
