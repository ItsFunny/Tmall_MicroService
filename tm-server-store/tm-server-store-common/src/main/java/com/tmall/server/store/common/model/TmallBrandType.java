package com.tmall.server.store.common.model;

import com.tmall.common.dto.BrandDTO;

public class TmallBrandType
{
	private Integer brandTypeId;

	private String brandTypeName;

	public BrandDTO to()
	{
		BrandDTO dto = new BrandDTO();
		dto.setBrandTypeId(this.brandTypeId);
		dto.setBrandTypeName(this.brandTypeName);
		return dto;
	}

	public Integer getBrandTypeId()
	{
		return brandTypeId;
	}

	public void setBrandTypeId(Integer brandTypeId)
	{
		this.brandTypeId = brandTypeId;
	}

	public String getBrandTypeName()
	{
		return brandTypeName;
	}

	public void setBrandTypeName(String brandTypeName)
	{
		this.brandTypeName = brandTypeName == null ? null : brandTypeName.trim();
	}
}