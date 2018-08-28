/**
*
* @author joker 
* @date 创建时间：2018年6月22日 下午4:29:59
* 
*/
package com.tmall.serer.spi.gateway.feign.product;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.ResultDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月22日 下午4:29:59
*/
@FeignClient("gateway")
public interface IProductServerFeignCategoryService
{
	@GetMapping("/product/category/topLevel/all")
	ResultDTO<Collection<CategoryDTO>>findAllTopLevelCategories();
	
	@GetMapping("/product/category/child/{categoryId}")
	ResultDTO<Collection<CategoryDTO>>findChildCategoriesWithinCategoryId(@PathVariable("categoryId")String categoryId);
}
