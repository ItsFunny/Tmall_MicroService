/**
*
* @author joker 
* @date 创建时间：2018年6月23日 上午11:41:05
* 
*/
package com.tmall.server.product.service;

import java.util.Collection;
import java.util.List;

import com.tmall.common.dto.BrandDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月23日 上午11:41:05
*/
public interface IBrandService
{
	Collection<BrandDTO>findAllBrands();
	
	
	List<BrandDTO>findStoreOperatedBrands(Long storeId);
}
