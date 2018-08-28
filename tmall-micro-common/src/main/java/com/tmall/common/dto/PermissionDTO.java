/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午9:36:25
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月2日 下午9:36:25
 * extends ResourceComponent
 */
public class PermissionDTO implements Serializable
{
	/**
	* @author joker 
	* @date 创建时间：2018年8月16日 下午2:44:27
	*/
	private static final long serialVersionUID = -8118989361482369464L;

	private Integer permissionId;

	private Integer permissionStatus;

	private String permissionName;
	
	private String permissionDescription;
	
	private Integer roleId;

	private List<ResourceDTO> resources;

	
	public PermissionDTO()
	{
		this.resources=new ArrayList<ResourceDTO>();
	}
	public Integer getPermissionId()
	{
		return permissionId;
	}

	public void setPermissionId(Integer permissionId)
	{
		this.permissionId = permissionId;
	}

	public Integer getPermissionStatus()
	{
		return permissionStatus;
	}

	public void setPermissionStatus(Integer permissionStatus)
	{
		this.permissionStatus = permissionStatus;
	}

	public String getPermissionName()
	{
		return permissionName;
	}

	public void setPermissionName(String permissionName)
	{
		this.permissionName = permissionName;
	}

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public List<ResourceDTO> getResources()
	{
		return resources;
	}

	public void setResources(List<ResourceDTO> resources)
	{
		this.resources = resources;
	}
	public String getPermissionDescription()
	{
		return permissionDescription;
	}
	public void setPermissionDescription(String permissionDescription)
	{
		this.permissionDescription = permissionDescription;
	}


}
