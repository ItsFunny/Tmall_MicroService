package com.tmall.server.store.common.model;

import com.tmall.common.dto.BrandDTO;

public class TmallBrand
{
	private Integer brandId;

	private String pictureId;

	private String brandName;

	private String brandDesc;

	private Integer brandTypeId;

	private String brandTypeName;

	private Integer brandDisSeq;

	public BrandDTO to()
	{
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setBrandId(this.brandId);
		brandDTO.setPictureId(this.pictureId);
		brandDTO.setBrandName(this.brandName);
		brandDTO.setBrandDesc(this.brandDesc);
		brandDTO.setBrandTypeId(this.brandTypeId);
		brandDTO.setBrandTypeName(this.brandTypeName);
		return brandDTO;
	}

	public void from(BrandDTO brandDTO)
	{
		this.brandId = brandDTO.getBrandId();
		this.pictureId = brandDTO.getPictureId();
		this.brandName = brandDTO.getBrandName();
		this.brandDesc = brandDTO.getBrandDesc();
		this.brandTypeId = brandDTO.getBrandTypeId();
		this.brandTypeName = brandDTO.getBrandTypeName();
	}

	public Integer getBrandId()
	{
		return brandId;
	}

	public void setBrandId(Integer brandId)
	{
		this.brandId = brandId;
	}

	public String getPictureId()
	{
		return pictureId;
	}

	public void setPictureId(String pictureId)
	{
		this.pictureId = pictureId == null ? null : pictureId.trim();
	}

	public String getBrandName()
	{
		return brandName;
	}

	public void setBrandName(String brandName)
	{
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getBrandDesc()
	{
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc)
	{
		this.brandDesc = brandDesc == null ? null : brandDesc.trim();
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

	public Integer getBrandDisSeq()
	{
		return brandDisSeq;
	}

	public void setBrandDisSeq(Integer brandDisSeq)
	{
		this.brandDisSeq = brandDisSeq;
	}
}