/**
*
* @author joker 
* @date 创建时间：2018年5月28日 上午8:30:05
* 
*/
package com.micro.portal.model;

import java.util.ArrayList;
import java.util.Collection;

import com.micro.product.common.dto.CategoryDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月28日 上午8:30:05
 */
public class PortalCategory
{
	private Integer categoryId;
	private String categoryName;
	private Collection<CategoryDTO> childCategories=new ArrayList<CategoryDTO>();
	
	public void from(CategoryDTO categoryDTO)
	{
		this.categoryId=categoryDTO.getCategoryId();
		this.categoryName=categoryDTO.getCategoryName();
	}
	
	
	public Integer getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public Collection<CategoryDTO> getChildCategories()
	{
		return childCategories;
	}

	public void setChildCategories(Collection<CategoryDTO> childCategories)
	{
		this.childCategories = childCategories;
	}


	@Override
	public String toString()
	{
		return "PortalCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", childCategories="
				+ childCategories + "]";
	}


	@Override
	public int hashCode()
	{
		int i=27;
		int result=47;
		result*=i+categoryId+result;
		return result;
	}


	@Override
	public boolean equals(Object obj)
	{
		PortalCategory category=(PortalCategory) obj;
		return category.getCategoryId().equals(this.getCategoryId());
	}

}
