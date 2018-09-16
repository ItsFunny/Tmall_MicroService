package com.tmall.server.product.common.model;

import java.util.Date;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.other.SQLExtentionModel;

public class TmallCategory extends SQLExtentionModel
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
		categoryDTO.setStatus(this.status);
		categoryDTO.setUpdateDate(this.updateDate);
		categoryDTO.setCreateTime(this.createTime);
		categoryDTO.setCreator(this.creator);
		categoryDTO.setCreatorUserId(this.creatorUserId);
		categoryDTO.setLastOperator(this.lastOperator);
		categoryDTO.setLastOperatorId(this.lastOperatorId);
		return categoryDTO;
	}
	public void from(CategoryDTO categoryDTO)
	{
		this.categoryDisplaySeq=categoryDTO.getCategoryDisplaySeq();
		this.categoryId=categoryDTO.getCategoryId();
		this.categoryName=categoryDTO.getCategoryName();
		this.categoryPid=categoryDTO.getCategoryPid();
		this.createDate=categoryDTO.getCreateDate();
		this.createTime=categoryDTO.getCreateTime();
		this.creator=categoryDTO.getCreator();
		this.creatorUserId=categoryDTO.getCreatorUserId();
		this.lastOperator=categoryDTO.getLastOperator();
		this.lastOperatorId=categoryDTO.getLastOperatorId();
		this.status=categoryDTO.getStatus();
		this.updateDate=categoryDTO.getUpdateDate();
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