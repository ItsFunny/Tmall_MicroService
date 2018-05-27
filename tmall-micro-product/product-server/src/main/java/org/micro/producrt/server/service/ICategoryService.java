/**
*
* @author joker 
* @date 创建时间：2018年5月25日 下午1:17:16
* 
*/
package com.micro.producrt.server.service;

import java.util.Collection;

import com.micro.product.common.dto.CategoryDTO;

/**
* @author joker 
* @date 创建时间：2018年5月25日 下午1:17:16
*/
public interface ICategoryService
{
	Integer countCateogry();
	Collection<CategoryDTO>findAll();
}
