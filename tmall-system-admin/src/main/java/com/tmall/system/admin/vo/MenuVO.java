/**
*
* @author joker 
* @date 创建时间：2018年8月17日 上午9:26:43
* 
*/
package com.tmall.system.admin.vo;

import java.util.ArrayList;
import java.util.List;

import com.tmall.common.dto.MenuDTO;
import com.tmall.common.dto.ResourceDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月17日 上午9:26:43
 */
public class MenuVO
{

	private Long menuId;
	
	private Long menuParentId;

	private String menuName;

	private List<MenuVO> childs;

	private String menuUrl;
	
	
	
	public void from(MenuDTO menuDTO)
	{
		this.menuId=menuDTO.getMenuId();
		this.menuName=menuDTO.getMenuName();
		this.menuUrl=menuDTO.getMenuUrl();
		this.menuParentId=menuDTO.getMenuParentId();
	}
	public MenuVO()
	{
		this.childs = new ArrayList<MenuVO>();
	}

	public void addChild(MenuVO menuVO)
	{
		this.childs.add(menuVO);
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	public List<MenuVO> getChilds()
	{
		return childs;
	}

	public void setChilds(List<MenuVO> childs)
	{
		this.childs = childs;
	}

	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	public Long getMenuId()
	{
		return menuId;
	}

	public void setMenuId(Long menuId)
	{
		this.menuId = menuId;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		return ((MenuVO) obj).getMenuId().equals(this.menuId);
	}
	public Long getMenuParentId()
	{
		return menuParentId;
	}
	public void setMenuParentId(Long menuParentId)
	{
		this.menuParentId = menuParentId;
	}

}
