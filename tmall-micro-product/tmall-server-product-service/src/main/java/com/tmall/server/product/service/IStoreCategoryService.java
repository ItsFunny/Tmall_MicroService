/**
*
* @author joker 
* @date 创建时间：2018年8月20日 上午11:27:33
* 
*/
package com.tmall.server.product.service;

import java.util.List;

import com.tmall.common.dto.CategoryDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月20日 上午11:27:33
*/
public interface IStoreCategoryService
{
	Integer CATEGOTY_TOP_LEVEL=0;
	
	
	List<CategoryDTO> findStoreAllTopLevelCategories(Long storeId);
}
