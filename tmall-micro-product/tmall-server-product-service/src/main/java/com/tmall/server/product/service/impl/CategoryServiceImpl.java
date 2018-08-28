/**
*
* @author joker 
* @date 创建时间：2018年5月25日 下午1:28:58
* 
*/
package com.tmall.server.product.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.product.dao.CategoryDao;
import com.tmall.server.product.dao.count.CategoryCountDao;
import com.tmall.server.product.service.IProductServerCategoryService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 下午1:28:58
 */
@Service
public class CategoryServiceImpl implements IProductServerCategoryService
{
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CategoryCountDao categoryCountDao;

	@Override
	public Integer countCateogry()
	{
		return categoryDao.countCategory();
	}

	@Override
	public Collection<CategoryDTO> findAll()
	{
		// Collection<CategoryDTO> findSons =
		// categoryDao.findChildInIDs(Arrays.asList(new Integer[] {1,5}));
		// findSons.stream().forEach(n->System.out.println(n.getCategoryName()));
		return categoryDao.findParentCategoryAndChildCategory();
	}

	@Override
	public Collection<CategoryDTO> findAllTopLevelCategories()
	{
		Integer countTopLevelCategory = categoryCountDao.countTopLevelCategory();
		if (countTopLevelCategory <= 0)
		{
			return null;
		}
		Collection<CategoryDTO> categories = categoryDao.findTopLevelCategories();
		return categories;
	}

	@Override
	public Collection<CategoryDTO> findChildCattegoriesWithinOneCategory(Integer categoryId)
	{
		Integer countChildCategory = categoryCountDao.countChildCategory(categoryId);
		if (countChildCategory < 1)
		{
			return null;
		}
		Collection<CategoryDTO> childs = categoryDao.findChildCategoriesWithinOneCategory(categoryId);
		return childs;
	}

}
