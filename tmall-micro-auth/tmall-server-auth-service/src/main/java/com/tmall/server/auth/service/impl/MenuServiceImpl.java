/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午11:42:50
* 
*/
package com.tmall.server.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.MenuDTO;
import com.tmall.server.auth.common.model.TmallMenu;
import com.tmall.server.auth.common.model.TmallMenuExample;
import com.tmall.server.auth.common.model.TmallRoleMenu;
import com.tmall.server.auth.common.model.TmallRoleMenuExample;
import com.tmall.server.auth.common.model.TmallRoleMenuExample.Criteria;
import com.tmall.server.auth.dao.MenuDao;
import com.tmall.server.auth.dao.TmallRoleMenuDao;
import com.tmall.server.auth.service.IMenuService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月21日 下午11:42:50
 */
@Service
public class MenuServiceImpl implements IMenuService
{
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private TmallRoleMenuDao roleMenuDao;

	@Override
	public List<MenuDTO> findMenusByRoleId(Long roleId)
	{
		TmallRoleMenuExample example = new TmallRoleMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<TmallRoleMenu> roleMenus = roleMenuDao.selectByExample(example);
		if (null == roleMenus || roleMenus.isEmpty())
		{
			return null;
		}
		List<Long> menuidList = new ArrayList<Long>();
		roleMenus.forEach(m -> {
			menuidList.add(m.getMenuId());
		});
		TmallMenuExample menuExample = new TmallMenuExample();
		com.tmall.server.auth.common.model.TmallMenuExample.Criteria menucCriteria = menuExample.createCriteria();
		menucCriteria.andMenuIdIn(menuidList);
		List<TmallMenu> menus = menuDao.selectByExample(menuExample);
		if (null == menus || menus.isEmpty())
		{
			return null;
		}
		List<MenuDTO> dtos = new ArrayList<>();
		menus.forEach(m -> {
			dtos.add(m.to());
		});
		return dtos;
	}
}
