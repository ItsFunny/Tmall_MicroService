/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午1:52:55
* 
*/
package com.tmall.common.dto;

import java.util.Date;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月25日 下午1:52:55
 */
public class ProductDTO
{
	private Long productId;
	private Integer categoryId;
	private Integer brandId;
	private String pictureId;
	private Integer productStatus = 0; // 0 未审核 1 上架 2.下架
	private Integer productIsHot=0;  //0 unhot 1 hot 
	private String productName;
	private String productDesc;
	private String productSubTitle;
	private String productAfterSell;
	private Date updateDate;
	private Date createDate;
	
	
	
	
	

	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(Long productId)
	{
		this.productId = productId;
	}

	public Integer getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
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
		this.pictureId = pictureId;
	}

	public Integer getProductStatus()
	{
		return productStatus;
	}

	public void setProductStatus(Integer productStatus)
	{
		this.productStatus = productStatus;
	}


	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public Integer getProductIsHot()
	{
		return productIsHot;
	}

	public void setProductIsHot(Integer productIsHot)
	{
		this.productIsHot = productIsHot;
	}

	public String getProductDesc()
	{
		return productDesc;
	}

	public void setProductDesc(String productDesc)
	{
		this.productDesc = productDesc;
	}

	public String getProductSubTitle()
	{
		return productSubTitle;
	}

	public void setProductSubTitle(String productSubTitle)
	{
		this.productSubTitle = productSubTitle;
	}

	public String getProductAfterSell()
	{
		return productAfterSell;
	}

	public void setProductAfterSell(String productAfterSell)
	{
		this.productAfterSell = productAfterSell;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

}
