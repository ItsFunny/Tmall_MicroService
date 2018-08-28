/**
*
* @author joker 
* @date 创建时间：2018年6月22日 下午4:29:59
* 
*/
package com.tmall.server.gateway.service.product;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.ResultDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月22日 下午4:29:59
*/
public interface IProductServerCategoryService
{
	ResultDTO<Collection<CategoryDTO>>findAllTopLevelCategories();
	
	ResultDTO<Collection<CategoryDTO>>findChildCategoriesWithinCategoryId(@PathVariable("categoryId")String categoryId);
}
