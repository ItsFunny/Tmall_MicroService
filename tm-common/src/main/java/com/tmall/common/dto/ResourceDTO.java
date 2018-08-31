/**
*
* @author joker 
* @date 创建时间：2018年8月10日 下午10:03:29
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月10日 下午10:03:29
 * extends ResourceComponent
 */
public class ResourceDTO implements Serializable
{
	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月16日 下午4:27:27
	*/
	private static final long serialVersionUID = -8789317662617366053L;

	private Long resourceId;

	private Long resourceParentId;

	private String resourceValue;

	private String resourceName;
	private String resourceDescription;
	private String resourceType;
	private String resourceUrl;

	private Integer permissionId;
	
	private List<ResourceDTO>childs;
	
	public void addChild(ResourceDTO resourceDTO)
	{
		this.childs.add(resourceDTO);
	}
	

	public ResourceDTO()
	{
		super();
		this.childs=new ArrayList<ResourceDTO>();
	}

	public Long getResourceId()
	{
		return resourceId;
	}

	public void setResourceId(Long resourceId)
	{
		this.resourceId = resourceId;
	}


	public String getResourceValue()
	{
		return resourceValue;
	}

	public void setResourceValue(String resourceValue)
	{
		this.resourceValue = resourceValue;
	}

	public String getResourceName()
	{
		return resourceName;
	}

	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}

	public String getResourceDescription()
	{
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription)
	{
		this.resourceDescription = resourceDescription;
	}

	public String getResourceType()
	{
		return resourceType;
	}

	public void setResourceType(String resourceType)
	{
		this.resourceType = resourceType;
	}

	public String getResourceUrl()
	{
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl)
	{
		this.resourceUrl = resourceUrl;
	}

	public Integer getPermissionId()
	{
		return permissionId;
	}

	public void setPermissionId(Integer permissionId)
	{
		this.permissionId = permissionId;
	}

	public Long getResourceParentId()
	{
		return resourceParentId;
	}

	public void setResourceParentId(Long resourceParentId)
	{
		this.resourceParentId = resourceParentId;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public List<ResourceDTO> getChilds()
	{
		return childs;
	}

	public void setChilds(List<ResourceDTO> childs)
	{
		this.childs = childs;
	}

}
