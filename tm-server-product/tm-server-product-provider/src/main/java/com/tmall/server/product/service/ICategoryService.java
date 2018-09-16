/**
*
* @author joker 
* @date 创建时间：2018年9月13日 下午1:42:38
* 
*/
package com.tmall.server.product.service;

import java.util.List;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageBaseService;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.common.model.TmallCategory;

/**
* 
* @author joker 
* @date 创建时间：2018年9月13日 下午1:42:38
*/
public interface ICategoryService  extends PageBaseService<List<CategoryDTO>>
{
	
	Integer insert(TmallCategory category);
	
	ResultDTO<String> insertOrUpdate(UserRecordAspectWrapper<CategoryDTO> wrapper);
	
	
	CategoryDTO findByCategoryId(Integer categoryId);
	
	
	CategoryDTO findCategoryFathers(Integer categoryId);
	
	
	
}
