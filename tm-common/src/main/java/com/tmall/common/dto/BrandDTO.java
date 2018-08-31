/**
*
* @author joker 
* @date 创建时间：2018年6月23日 上午11:05:09
* 
*/
package com.tmall.common.dto;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月23日 上午11:05:09
 */
public class BrandDTO
{
	private Integer brandId;
	private String pictureId;
	private String brandName;
	private String brandDesc;
	private Integer brandTypeId;

	// type
	private String brandTypeName;
	// picture
	private String pictureUrl;
	//store
	private Long storeId;
	

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
		this.pictureId = pictureId;
	}

	public String getBrandName()
	{
		return brandName;
	}

	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}

	public String getBrandDesc()
	{
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc)
	{
		this.brandDesc = brandDesc;
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
		this.brandTypeName = brandTypeName;
	}

	public String getPictureUrl()
	{
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl)
	{
		this.pictureUrl = pictureUrl;
	}

	public Long getStoreId()
	{
		return storeId;
	}

	public void setStoreId(Long storeId)
	{
		this.storeId = storeId;
	}

}
