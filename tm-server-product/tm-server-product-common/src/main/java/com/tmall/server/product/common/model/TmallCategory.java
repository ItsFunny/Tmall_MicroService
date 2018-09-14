package com.tmall.server.product.common.model;

import java.util.Date;

import com.tmall.common.dto.CategoryDTO;

public class TmallCategory
{
	private Integer categoryId;

	private String categoryName;

	private Integer categoryDisplaySeq;

	private Integer categoryPid;

	private Integer status;

	private String creator;

	private Long creatorUserId;

	private String lastOperator;

	private Long lastOperatorId;

	private Date createDate;

	private Date updateDate;

	private Integer createTime;

	public CategoryDTO to()
	{
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(this.categoryId);
		categoryDTO.setCategoryName(this.categoryName);
		categoryDTO.setCategoryPid(this.categoryPid);
		categoryDTO.setCreateDate(this.createDate);
		categoryDTO.setCategoryDisplaySeq(this.categoryDisplaySeq);
		return categoryDTO;
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
		this.categoryName = categoryName == null ? null : categoryName.trim();
	}

	public Integer getCategoryDisplaySeq()
	{
		return categoryDisplaySeq;
	}

	public void setCategoryDisplaySeq(Integer categoryDisplaySeq)
	{
		this.categoryDisplaySeq = categoryDisplaySeq;
	}

	public Integer getCategoryPid()
	{
		return categoryPid;
	}

	public void setCategoryPid(Integer categoryPid)
	{
		this.categoryPid = categoryPid;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getCreator()
	{
		return creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator == null ? null : creator.trim();
	}

	public Long getCreatorUserId()
	{
		return creatorUserId;
	}

	public void setCreatorUserId(Long creatorUserId)
	{
		this.creatorUserId = creatorUserId;
	}

	public String getLastOperator()
	{
		return lastOperator;
	}

	public void setLastOperator(String lastOperator)
	{
		this.lastOperator = lastOperator == null ? null : lastOperator.trim();
	}

	public Long getLastOperatorId()
	{
		return lastOperatorId;
	}

	public void setLastOperatorId(Long lastOperatorId)
	{
		this.lastOperatorId = lastOperatorId;
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

	public Integer getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Integer createTime)
	{
		this.createTime = createTime;
	}
}