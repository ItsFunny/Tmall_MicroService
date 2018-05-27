/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午9:52:38
* 
*/
package com.micro.producrt.server.model;

import java.util.Date;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午9:52:38
 */
public class TmallCategory
{
	private Integer categoryId;
	private Integer parentCateogryId;
	private String categoryName;
	private Date createDate;
	private Date updateDate;
	public Integer getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	public Integer getParentCateogryId()
	{
		return parentCateogryId;
	}

	public void setParentCateogryId(Integer parentCateogryId)
	{
		this.parentCateogryId = parentCateogryId;
	}

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

}
