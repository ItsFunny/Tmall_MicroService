/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午9:52:14
* 
*/
package com.tmall.facade.service;

import java.util.List;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午9:52:14
*/
public interface ICategoryService
{
	ResultDTO<PageResponseDTO<List<CategoryDTO>>>findByPage(PageRequestDTO pageRequestDTO);
}
