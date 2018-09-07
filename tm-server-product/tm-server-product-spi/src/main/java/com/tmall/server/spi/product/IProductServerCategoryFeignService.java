/**
*
* @author joker 
* @date 创建时间：2018年8月20日 下午12:38:45
* 
*/
package com.tmall.server.spi.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.CategoryDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月20日 下午12:38:45
*/
@FeignClient(name="product")
public interface IProductServerCategoryFeignService
{
	@RequestMapping(value="/category/topLevel/all/{storeId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<CategoryDTO>>findStoreAllTopLevelCategories(@PathVariable("storeId")Long storeId);
	
	@RequestMapping(value="/category/child/{categoryPid}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<CategoryDTO>>findCateogryChilds(@PathVariable("cateogryPid")Integer categoryPid);
	

}
