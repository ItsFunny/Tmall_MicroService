/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午10:03:29
* 
*/
package com.tmall.server.spi.gateway.category;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午10:03:29
*/
@FeignClient(name="gateway")
public interface IGatewayCategoryService
{
	@PostMapping(value="/valid/category/show",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<CategoryDTO>>>findCategoriesByPage(@RequestBody PageRequestDTO pageRequestDTO);

	@GetMapping(value="/valid/category/parents/{categoryId}")
	ResultDTO<CategoryDTO>findCategoryParents(@PathVariable("categoryId")Integer categoryId);
	
	@PostMapping(value="/valid/category/addOrUpdate",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String>addOrUpdateCategory(@RequestBody UserRequestDTO dto);
}
