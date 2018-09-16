/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午9:53:25
* 
*/
package com.tmall.facade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.facade.service.ICategoryService;
import com.tmall.server.spi.product.IProductServerCategoryFeignService;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午9:53:25
*/
@Service
public class CategoryServiceImpl implements ICategoryService
{
	@Autowired
	private IProductServerCategoryFeignService categoryFeignService;

	@Override
	public ResultDTO<PageResponseDTO<List<CategoryDTO>>> findByPage(PageRequestDTO pageRequestDTO)
	{
		return categoryFeignService.findCategoriesByPage(pageRequestDTO);
	}

	@Override
	public ResultDTO<CategoryDTO> findCateogoryParents(Integer categoryId)
	{
		return categoryFeignService.findCategoryParents(categoryId);
	}

	@Override
	public ResultDTO<String> addOrUpdateCategory(UserRequestDTO dto)
	{
		return categoryFeignService.addOrUpdateCategory(dto);
	}

}
