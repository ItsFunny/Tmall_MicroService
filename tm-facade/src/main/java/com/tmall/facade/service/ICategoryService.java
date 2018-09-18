/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午9:52:14
* 
*/
package com.tmall.facade.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午9:52:14
*/
public interface ICategoryService
{
	ResultDTO<PageResponseDTO<List<CategoryDTO>>>findByPage(PageRequestDTO pageRequestDTO);
	/*
	 * 显示某个类目的所有祖宗
	 */
	ResultDTO<CategoryDTO>findCateogoryParents(Integer categoryId);
	/*
	 * 添加或者更新类目
	 */
	ResultDTO<String>addOrUpdateCategory( UserRequestDTO dto);
	/*
	 * 查询某个类目下的所有子类目
	 */
	ResultDTO<List<CategoryDTO>>findCategoryAllChilds(Integer categoryId);
}
