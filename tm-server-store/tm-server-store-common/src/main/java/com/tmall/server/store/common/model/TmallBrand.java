package com.tmall.server.store.common.model;

import com.tmall.common.dto.BrandDTO;

import lombok.Data;

@Data
public class TmallBrand
{
	private Integer brandId;

	private String pictureId;

	private String brandName;

	private String brandDesc;

	private Integer brandTypeId;

	private String brandTypeName;

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
	public  void from(BrandDTO brandDTO)
	{
		this.brandId=brandDTO.getBrandId();
		this.pictureId=brandDTO.getPictureId();
		this.brandName=brandDTO.getBrandName();
		this.brandDesc=brandDTO.getBrandDesc();
		this.brandTypeId=brandDTO.getBrandTypeId();
		this.brandTypeName=brandDTO.getBrandTypeName();
	}

}