/**
*
* @author joker 
* @date 创建时间：2018年8月16日 下午6:26:27
* 
*/
package com.tmall.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
* 
* @author joker 
* @date 创建时间：2018年8月16日 下午6:26:27
*/
public abstract class RoleComponent extends TmallTreeComposite
{

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月16日 下午6:26:49
	*/
	private static final long serialVersionUID = -4464058499461633126L;
	
	//包括权限和资源,通常而言都是权限
	private List<ResourceComponent>resources;
	
	public RoleComponent()
	{
		super();
		this.resources=new ArrayList<ResourceComponent>();
	}
	
	public void addResource(ResourceComponent component)
	{
		this.resources.add(component);
	}
	public List<ResourceComponent>getResources()
	{
		return this.resources;
	}

	public void setResources(List<ResourceComponent> resources)
	{
		this.resources = resources;
	}
	

}
