/**
*
* @author joker 
* @date 创建时间：2018年9月15日 上午8:13:30
* 
*/
package com.tmall.system.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.constants.UserRequestConstant;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.spi.gateway.category.IGatewayCategoryService;

import antlr.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月15日 上午8:13:30
 */
@RestController
@RequestMapping(value="/admin/category")
public class CategoryController
{
	@Autowired
	private IGatewayCategoryService gatewayCategoryService;
	
	/*
	 * 显示所有和分类放一起是不是不怎么好啊
	 */
	@RequiresPermissions(value= {"edit_product_category"})
	@GetMapping(value = "/all")
	public ModelAndView showCategories(@RequestParam(name = "pageSize", defaultValue = "10") String pageSizeStr,
			@RequestParam(name = "pageNum", defaultValue = "1") String pageNumStr, HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, Object> params)
	{
		ModelAndView modelAndView = null;
		Integer pageSize = Integer.parseInt(pageSizeStr);
		Integer pageNum = Integer.parseInt(pageNumStr);
		String searchKey=(String) params.get("searchKey");
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		pageRequestDTO.setPageSize(pageSize);
		pageRequestDTO.setPageNum(pageNum);
		Map<String, Object>condition=new HashMap<String, Object>();
		if(!org.apache.commons.lang3.StringUtils.isEmpty(searchKey))
		{
			params.put("searchKey", searchKey);
			try
			{
				Integer id=Integer.parseInt(searchKey);
				//则直接按照主键查找
				pageRequestDTO.setSingal(true);
				pageRequestDTO.setSingleKey(id);
			} catch (Exception e)
			{
				condition.put("searchKey", searchKey);
				pageRequestDTO.setData(condition);
			}
		}
		pageRequestDTO.setTablePrefixName(SQLExtentionConstant.CATEGORY);
		ResultDTO<PageResponseDTO<List<CategoryDTO>>> pageRes = gatewayCategoryService
				.findCategoriesByPage(pageRequestDTO);
		if (!pageRes.isSuccess())
		{
			params.put("error", pageRes.getMsg());
			modelAndView = new ModelAndView("error", params);
		} else
		{
			params.put("pageVO", pageRes.getData());
			modelAndView = new ModelAndView("categories", params);
		}
		return modelAndView;
	}
	private void loopFind(CategoryDTO dto,Stack<CategoryDTO>stack)
	{
		stack.push(dto);
		if(dto.getParent()!=null)
		{
			loopFind(dto.getParent(),stack);
		}
		
	}
	@GetMapping(value="/{categoryId}/detail",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView showCategoryDetailChilds(@PathVariable("categoryId")Integer categoryId)
	{
		ModelAndView modelAndView=null;
		//查询所有的父类目
		ResultDTO<CategoryDTO> parentsRes = gatewayCategoryService.findCategoryParents(categoryId);
		ResultDTO<List<CategoryDTO>> childsRes = gatewayCategoryService.findCategoryChilds(categoryId);
		if(childsRes.isSuccess()&&parentsRes.isSuccess())
		{
			modelAndView =new ModelAndView("categories_detail");
			modelAndView.addObject("childItems",childsRes.getData());
			//因为freemakrer好像无法做到递归,那只能通过在内存中解析了
			Stack<CategoryDTO>stack=new Stack<>();
			loopFind(parentsRes.getData()	, stack);
			modelAndView.addObject("selfItem",parentsRes.getData());
			modelAndView.addObject("parentItems",stack);
		}else {
			modelAndView=new ModelAndView("error");
			modelAndView.addObject("error","查询数据出错");
		}
		return modelAndView;
	}
	
}
