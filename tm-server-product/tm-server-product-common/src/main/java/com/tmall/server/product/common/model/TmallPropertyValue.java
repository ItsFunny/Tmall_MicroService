package com.tmall.server.product.common.model;

import org.springframework.beans.BeanUtils;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.tmall.common.dto.PropertyDTO.PropertyValueDTO;

public class TmallPropertyValue extends AbstractSQLExtentionModel
{
	private Long propertyValueId;

	private Integer propertyId;

	private String propertyValue;

	private Integer propertyDisSeq;
	
	public void from(PropertyValueDTO valueDTO)
	{
		BeanUtils.copyProperties(valueDTO, this);
	}

	public Long getPropertyValueId()
	{
		return propertyValueId;
	}

	public void setPropertyValueId(Long propertyValueId)
	{
		this.propertyValueId = propertyValueId;
	}

	public Integer getPropertyId()
	{
		return propertyId;
	}

	public void setPropertyId(Integer propertyId)
	{
		this.propertyId = propertyId;
	}

	public String getPropertyValue()
	{
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue)
	{
		this.propertyValue = propertyValue == null ? null : propertyValue.trim();
	}

	public Integer getPropertyDisSeq()
	{
		return propertyDisSeq;
	}

	public void setPropertyDisSeq(Integer propertyDisSeq)
	{
		this.propertyDisSeq = propertyDisSeq;
	}

	@Override
	public Number getUniquekey()
	{
		return this.propertyValueId;
	}
}