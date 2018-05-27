/**
*
* @author joker 
* @date 创建时间：2018年5月25日 下午1:28:58
* 
*/
package com.micro.producrt.server.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.producrt.server.dao.CategoryDao;
import com.micro.producrt.server.service.ICategoryService;
import com.micro.product.common.dto.CategoryDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 下午1:28:58
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Integer countCateogry()
	{
		return categoryDao.countCategory();
	}

	@Override
	public Collection<CategoryDTO> findAll()
	{
		return categoryDao.findAll();
	}

}
