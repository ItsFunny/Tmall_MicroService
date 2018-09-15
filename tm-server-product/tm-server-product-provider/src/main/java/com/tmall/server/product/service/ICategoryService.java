/**
*
* @author joker 
* @date 创建时间：2018年9月13日 下午1:42:38
* 
*/
package com.tmall.server.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joker.library.page.PageBaseService;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.product.common.model.TmallCategory;

/**
* 
* @author joker 
* @date 创建时间：2018年9月13日 下午1:42:38
*/
public interface ICategoryService  extends PageBaseService<List<CategoryDTO>>
{
	
	Integer insert(TmallCategory category);
}
