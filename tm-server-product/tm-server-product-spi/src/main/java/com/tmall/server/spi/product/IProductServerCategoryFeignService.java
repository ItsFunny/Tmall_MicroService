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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月20日 下午12:38:45
 */
@FeignClient(name = "product")
public interface IProductServerCategoryFeignService
{
	@RequestMapping(value = "/category/topLevel/all/{storeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<CategoryDTO>> findStoreAllTopLevelCategories(@PathVariable("storeId") Long storeId);

	@RequestMapping(value = "/category/child/{categoryPid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<CategoryDTO>> findCateogryChilds(@PathVariable("cateogryPid") Integer categoryPid);

	/*
	 * 分页显示类目
	 */
	@PostMapping(value = "/auth/category/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<CategoryDTO>>> findCategoriesByPage(@RequestBody PageRequestDTO pageRequestDTO);
	/*
	 * 显示某个类目下的所有父类
	 */
	@GetMapping(value="/auth/category/fathers/{categoryId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<CategoryDTO>findCategoryParents(@PathVariable("categoryId")Integer categoryId);
	/*
	 * 添加或者更新类目
	 */
	@PostMapping(value="/auth/category/addOrUpdate",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String>addOrUpdateCategory(@RequestBody UserRequestDTO dto);

}
