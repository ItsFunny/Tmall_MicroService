/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午4:05:47
* 
*/
package com.tmall.server.product.server.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.product.service.IProductServerCategoryService;
import com.tmall.server.product.service.IStoreCategoryService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月13日 下午4:05:47
 */
@RestController
@CrossOrigin(value="http://localhost:9001")
public class CategoryAPIController
{
	@Autowired
	private IProductServerCategoryService categoryService;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;
	@Autowired
	private IStoreCategoryService storeCategoryService;
	
	/*
	 * show all topLevel categories within the storeId
	 */
	@RequestMapping("/category/topLevel/all/{storeId}")
	public ResultDTO<Collection<CategoryDTO>>allCategories(@PathVariable("storeId")Long  storeId)
	{
		List<CategoryDTO> categoryDTOs = storeCategoryService.findStoreAllTopLevelCategories(storeId);
		return ResultUtils.sucess(categoryDTOs);
	}
	/*
	 * show child categories within the category_pid
	 */
	@RequestMapping("/category/child/{categoryPid}")
	public ResultDTO<Collection<CategoryDTO>>findChildCategories( @PathVariable(name="categoryPid") Integer categoryPid)
	{
		Collection<CategoryDTO> childs = categoryService.findChildCattegoriesWithinOneCategory(categoryPid);
		return ResultUtils.sucess(childs);
	}
	/*
	 * 展示首页的左边类目
	 */
//	@RequestMapping("/category/all")
//	public ResultDTO<Collection<CategoryDTO>> findAllCategories()
//	{
//		Collection<CategoryDTO> all = categoryService.findAll();
//		return ResultUtils.sucess(all);
//	}
//	@RequestMapping("/test")
//	public String test()
//	{
//		System.out.println(tmallConfigProperty.getUrl());
//		return tmallConfigProperty.getUrl();
//	}
}
