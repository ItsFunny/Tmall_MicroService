/**
*
* @author joker 
* @date 创建时间：2018年9月5日 上午9:12:25
* 
*/
package com.tmall.system.admin.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.tmall.common.dto.BrandDTO;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 上午9:12:25
*/

@Data
public class BrandFormModel
{
	
	private Integer brandId;
	@NotNull
	private String brandName;
	
	@NotNull
	private String brandDesc;
	
	@Max(value=100,message="排序号最大不能超过100")
	private Integer brandDisSeq;
	
	@NotNull
	private Integer brandType;
	
	public void to(BrandDTO brandDTO)
	{
		brandDTO.setBrandName(this.brandName);
		brandDTO.setBrandDesc(this.brandDesc);
		brandDTO.setBrandDisSeq(this.brandDisSeq);
		brandDTO.setBrandId(this.brandId);
	}
}
