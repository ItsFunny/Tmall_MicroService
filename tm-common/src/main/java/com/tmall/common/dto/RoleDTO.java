/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午9:32:22
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月2日 下午9:32:22
 * extends RoleComponent
 */
public class RoleDTO implements Serializable
{
	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月16日 下午2:44:44
	*/
	private static final long serialVersionUID = -1042480265576245720L;

	private Long roleId;

	private Long storeId;

	private Integer roleStatus;

	private String roleName;

	private String roleDescription;
	
	//2018-08-22 09:30 add
	private List<MenuDTO>menus;
	private List<ActionDTO>permisions;

	private Long parentUserId;
	
	public boolean isSuperAdmin()
	{
		return roleId==0?true:false;
	}

	public RoleDTO()
	{
		super();
		this.permisions=new ArrayList<ActionDTO>();
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getRoleDescription()
	{
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription)
	{
		this.roleDescription = roleDescription;
	}

	public Long getParentUserId()
	{
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId)
	{
		this.parentUserId = parentUserId;
	}

	public Integer getRoleStatus()
	{
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus)
	{
		this.roleStatus = roleStatus;
	}

	public Long getStoreId()
	{
		return storeId;
	}

	public void setStoreId(Long storeId)
	{
		this.storeId = storeId;
	}

	public Long getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Long roleId)
	{
		this.roleId = roleId;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public List<ActionDTO> getPermisions()
	{
		return permisions;
	}

	public void setPermisions(List<ActionDTO> permisions)
	{
		this.permisions = permisions;
	}

	public List<MenuDTO> getMenus()
	{
		return menus;
	}

	public void setMenus(List<MenuDTO> menus)
	{
		this.menus = menus;
	}

}
