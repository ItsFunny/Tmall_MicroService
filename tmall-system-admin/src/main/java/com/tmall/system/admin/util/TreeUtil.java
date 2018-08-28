/**
*
* @author joker 
* @date 创建时间：2018年8月22日 上午9:43:43
* 
*/
package com.tmall.system.admin.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.tmall.common.dto.MenuDTO;
import com.tmall.system.admin.vo.MenuVO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月22日 上午9:43:43
 */
public class TreeUtil
{
	// public static Collection<MenuVO> spliceMenuTree(List<MenuDTO> menus)
	// {
	// Map<Long, MenuVO> menuMap = new HashMap<Long, MenuVO>();
	// for (MenuDTO menuDTO : menus)
	// {
	// MenuVO menuVO = new MenuVO();
	// menuVO.from(menuDTO);
	// Long menuParentId = menuVO.getMenuParentId();
	//
	// if (null == menuParentId)
	// {
	// if (menuMap.containsKey(menuParentId))
	// {
	// MenuVO tMenu = menuMap.get(menuParentId);
	// menuVO.setChilds(tMenu.getChilds());
	// }
	// menuMap.put(menuParentId, menuVO);
	// } else
	// {
	// MenuVO tMenu = null;
	// if (menuMap.containsKey(menuParentId))
	// {
	// tMenu = menuMap.get(menuParentId);
	// } else
	// {
	// tMenu = new MenuVO();
	// }
	// tMenu.addChild(menuVO);
	// menuMap.put(menuParentId, tMenu);
	// }
	// }
	// return menuMap.values();
	// }
	//
	// public static Collection<MenuVO> spliceVOs(List<MenuDTO> menuDTOs)
	// {
	// List<MenuVO> menuVOs = Lists.newArrayList();
	// menuVOs.forEach(v -> {
	//
	// });
	// for (MenuDTO menuDTO : menuDTOs)
	// {
	// MenuVO menuVO = new MenuVO();
	// menuVO.from(menuDTO);
	// if (null == menuDTO.getMenuParentId())
	// {
	// menuVOs.add(menuVO);
	// continue;
	// }
	// menuVOs.forEach(p -> {
	//
	// });
	// }
	// return menuVOs;
	// }
	//
	//// public static Collection<MenuVO> rescursList(List<MenuDTO> menuDTOs, Long
	// parentId)
	//// {
	////
	//// }
	//
	// public static Collection<MenuDTO> getChildList(List<MenuDTO> menuDTOs, Long
	// parentId)
	// {
	// List<MenuDTO> tList = new ArrayList<>();
	// for (MenuDTO menuDTO : menuDTOs)
	// {
	// if (menuDTO.getMenuParentId() == null)
	// {
	// continue;
	// } else
	// {
	// if (menuDTO.getMenuParentId().equals(parentId))
	// {
	// tList.add(menuDTO);
	// }
	// }
	// }
	// return tList;
	// }

	public static List<MenuVO> spliceMenus(List<MenuDTO> menuDTOs)
	{
		List<MenuVO> vos = new ArrayList<>();

		for (MenuDTO menuDTO : menuDTOs)
		{
			MenuVO menuVO = new MenuVO();
			menuVO.from(menuDTO);
			if (menuVO.getMenuParentId() == null || menuVO.getMenuParentId() == 0)
			{
				vos.add(menuVO);
				continue;
			}
			// for (MenuVO v : vos)
			// {
			// List<MenuVO> childs = v.getChilds();
			// if (v.getMenuId().equals(menuVO.getMenuParentId()))
			// {
			// childs.add(menuVO);
			// } else
			// {
			// for (MenuVO c : childs)
			// {
			// if (c.getMenuParentId().equals(menuVO.getMenuParentId()))
			// {
			// c.getChilds().add(menuVO);
			// }
			// }
			// }
			// }

			vos.forEach(v -> {
				List<MenuVO> childs = v.getChilds();
				if (v.getMenuId().equals(menuVO.getMenuParentId()))
				{
					childs.add(menuVO);
				} else
				{
					childs.forEach(c -> {
						if (c.getMenuId().equals(menuVO.getMenuParentId()))
						{
							c.getChilds().add(menuVO);
						}
					});
				}
			});
		}
		return vos;
	}
}
