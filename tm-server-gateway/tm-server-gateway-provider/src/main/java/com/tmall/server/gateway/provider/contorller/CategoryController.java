/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午10:00:19
* 
*/
package com.tmall.server.gateway.provider.contorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.facade.service.IFacadedService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月14日 下午10:00:19
 */
@RestController
@RequestMapping(value = "/valid/category")
public class CategoryController
{

	@Autowired
	private IFacadedService facadedService;

	@PostMapping(value = "/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<CategoryDTO>>> findCategoriesByPage(
			@RequestBody PageRequestDTO pageRequestDTO)
	{
		return facadedService.findCategoriesByPage(pageRequestDTO);
	}

	@GetMapping(value = "/parents/{categoryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<CategoryDTO> findCategoryParents(@PathVariable("categoryId") Integer categoryId)
	{
		return facadedService.findCategoryParents(categoryId);
	}

	@PostMapping(value = "/addOrUpdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> addOrUpdateCategory(@RequestBody UserRequestDTO dto)
	{
		return facadedService.addOrUpdateCat(dto);
	}

	@GetMapping(value = "/{categoryId}/childs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<CategoryDTO>> findCategoryAllChilds(@PathVariable("categoryId") Integer categoryId)
	{
		return facadedService.findCategoryAllChilds(categoryId);
	}

	@PostMapping(value = "/condition", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<CategoryDTO>> findCategoriesOnCondition(@RequestBody Map<String, Object> condition)
	{
		return facadedService.findCategoriesOnCondition(condition);
	}

	@PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<Integer> deleteCategories(@RequestBody UserRequestDTO userRequestDTO)
	{
		return facadedService.deleteCategoriesInBatch(userRequestDTO);
	}
}
