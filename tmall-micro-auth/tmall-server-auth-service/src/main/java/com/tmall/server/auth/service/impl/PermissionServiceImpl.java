/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午8:11:09
* 
*/
package com.tmall.server.auth.service.impl;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.ActionDTO;
import com.tmall.server.auth.common.model.TmallAction;
import com.tmall.server.auth.common.model.TmallActionExample;
import com.tmall.server.auth.common.model.TmallRoleAction;
import com.tmall.server.auth.common.model.TmallRoleActionExample;
import com.tmall.server.auth.common.model.TmallRoleActionExample.Criteria;
import com.tmall.server.auth.dao.PermissionDao;
import com.tmall.server.auth.dao.TmallRoleActionDao;
import com.tmall.server.auth.service.IPermissionService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月2日 下午8:11:09
 */
@Service
public class PermissionServiceImpl implements IPermissionService
{
	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private TmallRoleActionDao tmallRoleActionDao;

	// 这里后半段的是重复的,感觉可以通过一个baseservice实现
	@Override
	public List<ActionDTO> findRolePermissionByRoleId(Long roleId, Integer status)
	{
		TmallRoleActionExample example = new TmallRoleActionExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<TmallRoleAction> roleActions = tmallRoleActionDao.selectByExample(example);
		if (null == roleActions || roleActions.isEmpty())
		{
			return null;
		}
		List<Long> actionIdList = new ArrayList<Long>();
		roleActions.forEach(a -> {
			actionIdList.add(a.getActionId());
		});
		TmallActionExample actionExample = new TmallActionExample();
		com.tmall.server.auth.common.model.TmallActionExample.Criteria actionCriteria = actionExample.createCriteria();
		actionCriteria.andActionIdIn(actionIdList);
		if (null != status)
		{
			actionCriteria.andActionStatusEqualTo(status);
		}
		List<TmallAction> actions = permissionDao.selectByExample(actionExample);
		List<ActionDTO> actionDTOs = new ArrayList<>();
		if (null == actionCriteria || actions.isEmpty())
		{
			return null;
		}
		actions.forEach(a -> {
			actionDTOs.add(a.to());
		});
		return actionDTOs;
	}

	// @Override
	// public List<PermissionDTO> findPermissionsByRoleId(Integer roleId, Integer
	// status)
	// {
	// return permissionDao.findRolePermissionsBySingleRoleId(roleId, status);
	// }
	//
	// @Override
	// public List<TmallPermission> findPermissionsByRoleIdList(List<Integer>
	// roleIdList, Integer status)
	// {
	// return permissionDao.findRolePermissionsByRoleIdList(roleIdList, status);
	// }
}
