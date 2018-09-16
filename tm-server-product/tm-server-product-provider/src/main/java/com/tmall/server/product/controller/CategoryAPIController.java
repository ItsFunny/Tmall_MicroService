package com.tmall.server.product.controller;
/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午4:05:47
* 
*/

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.service.IdWorkerService;
import com.tmall.common.annotation.ArgumentCheckAnnotation;
import com.tmall.common.constants.UserRequestConstant;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.service.ICategoryService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月13日 下午4:05:47
 */
@RestController
@CrossOrigin(value = "http://localhost:9001")
@RequestMapping(value = "/auth/category")
public class CategoryAPIController
{
	// @Autowired
	// private IProductServerCategoryService categoryService;
	// @Autowired
	// private TmallConfigProperty tmallConfigProperty;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IdWorkerService idWorkerService;

	// @Autowired
	// private IStoreCategoryService storeCategoryService;

	/*
	 * show all topLevel categories within the storeId
	 */
	// @RequestMapping("/category/topLevel/all/{storeId}")
	// public
	// ResultDTO<Collection<CategoryDTO>>allCategories(@PathVariable("storeId")Long
	// storeId)
	// {
	// List<CategoryDTO> categoryDTOs =
	// storeCategoryService.findStoreAllTopLevelCategories(storeId);
	// return ResultUtils.sucess(categoryDTOs);
	// }
	/*
	 * show child categories within the category_pid
	 */
	// @RequestMapping("/category/child/{categoryPid}")
	// public ResultDTO<Collection<CategoryDTO>>findChildCategories(
	// @PathVariable(name="categoryPid") Integer categoryPid)
	// {
	// Collection<CategoryDTO> childs =
	// categoryService.findChildCattegoriesWithinOneCategory(categoryPid);
	// return ResultUtils.sucess(childs);
	// }
	/*
	 * 展示首页的左边类目
	 */
	// @RequestMapping("/category/all")
	// public ResultDTO<Collection<CategoryDTO>> findAllCategories()
	// {
	// Collection<CategoryDTO> all = categoryService.findAll();
	// return ResultUtils.sucess(all);
	// }
	// @RequestMapping("/test")
	// public String test()
	// {
	// System.out.println(tmallConfigProperty.getUrl());
	// return tmallConfigProperty.getUrl();
	// }

	/*
	 * 分页显示类目
	 */
	@PostMapping(value = "/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<CategoryDTO>>> showCategories(@RequestBody PageRequestDTO pageRequestDTO)
	{

		return ResultUtils.sucess(categoryService.findByPage(pageRequestDTO));
	}

	@GetMapping(value = "/fathers/{categoryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<CategoryDTO> findCategoryFathers(@PathVariable("categoryId") Integer categoryId)
	{
		CategoryDTO categoryDTO = categoryService.findCategoryFathers(categoryId);
		if (null == categoryDTO)
		{
			return ResultUtils.fail("categoryId对应的数据不存在");
		} else
		{
			return ResultUtils.sucess(categoryDTO);
		}
	}

	@ArgumentCheckAnnotation(parseType=CategoryDTO.class, mapKey = UserRequestConstant.USER_REQUEST_CATEGORY)
	@PostMapping(value = "/addOrUpdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> addOrUpdateCategory(@RequestBody UserRequestDTO dto)
	{
		Map<String, Object> extProps = dto.getExtProps();
		
		Object object = extProps.get(UserRequestConstant.USER_REQUEST_CATEGORY);
		ObjectMapper mapper=new ObjectMapper();
		CategoryDTO categoryDTO = mapper.convertValue(object, CategoryDTO.class);
		String detail = "";
		if (categoryDTO.getCategoryId() == null)
		{
			detail="新增一个类目:"+categoryDTO;
		}else {
			detail="更新一个类目为:"+categoryDTO;
		}
		UserRecordAspectWrapper<CategoryDTO> wrapper = new UserRecordAspectWrapper<CategoryDTO>(dto.getUser(), detail,
				TmallMQEnum.USER_RECORD.getExchangeName(), TmallMQEnum.USER_RECORD.getRoutinKey(), categoryDTO);
		return categoryService.insertOrUpdate(wrapper);
	}
}
