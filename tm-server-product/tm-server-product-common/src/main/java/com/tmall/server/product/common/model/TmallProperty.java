package com.tmall.server.product.common.model;

import com.tmall.common.other.SQLExtentionModel;

public class TmallProperty extends SQLExtentionModel
{
	private Integer propertyId;

	private String propertyName;

	private Boolean propertyIsSearch;

	public Integer getPropertyId()
	{
		return propertyId;
	}

	public void setPropertyId(Integer propertyId)
	{
		this.propertyId = propertyId;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName == null ? null : propertyName.trim();
	}

	public Boolean getPropertyIsSearch()
	{
		return propertyIsSearch;
	}

	public void setPropertyIsSearch(Boolean propertyIsSearch)
	{
		this.propertyIsSearch = propertyIsSearch;
	}
}