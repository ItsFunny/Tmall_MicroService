/**
*
* @author joker 
* @date 创建时间：2018年6月23日 上午11:05:09
* 
*/
package com.tmall.common.dto;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月23日 上午11:05:09
 */
@Data
public class BrandDTO
{
	private Integer brandId;
	private String pictureId;
	private String brandName;
	private String brandDesc;
	private Integer brandTypeId;
	//排序号
	private Integer brandDisSeq;
	

	// type
	private String brandTypeName;
	// picture
	private String pictureUrl;
	//store
	private Long storeId;
	
	
	


}
