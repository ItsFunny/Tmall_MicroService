/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:44:47
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午10:44:47
 */
public class CategoryDTO implements Serializable
{
	/**
	* 
	* @author joker 
	* @date 创建时间：2018年5月28日 下午1:27:47
	*/
	private static final long serialVersionUID = -274465288896692706L;
	private Integer categoryId;
	private Integer categoryPid;
	private String categoryName;
	private Integer displaySequence;

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

	public Integer getDisplaySequence()
	{
		return displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence)
	{
		this.displaySequence = displaySequence;
	}


	@Override
	public int hashCode()
	{
		int i=27;
		int result=17;
		result*=i+this.getCategoryId()+result;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		CategoryDTO categoryDTO = (CategoryDTO) obj;
		return this.getCategoryId().equals(categoryDTO.getCategoryId());
	}

	
	public Integer getCategoryPid()
	{
		return categoryPid;
	}

	public void setCategoryPid(Integer categoryPid)
	{
		this.categoryPid = categoryPid;
	}

	@Override
	public String toString()
	{
		return "CategoryDTO [categoryId=" + categoryId + ", categoryPid=" + categoryPid + ", categoryName="
				+ categoryName + ", displaySequence=" + displaySequence + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}

}
