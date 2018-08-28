/**
*
* @author joker 
* @date 创建时间：2018年5月25日 下午1:17:16
* 
*/
package com.tmall.server.product.service;

import java.util.Collection;

import com.tmall.common.dto.CategoryDTO;



/**
* @author joker 
* @date 创建时间：2018年5月25日 下午1:17:16
*/
public interface IProductServerCategoryService
{
	Integer countCateogry();
	Collection<CategoryDTO>findAll();
	
	Collection<CategoryDTO>findAllTopLevelCategories();
	
	Collection<CategoryDTO>findChildCattegoriesWithinOneCategory(Integer categoryId);
}
