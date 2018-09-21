package com.tmall.server.product.common.model;

import java.util.Date;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.other.SQLExtentionModel;

import lombok.Data;

@Data
public class TmallCategory extends AbstractSQLExtentionModel
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

	@Override
	public Number getUniquekey()
	{
		return this.categoryId;
	}
}