/**
*
* @author joker 
* @date 创建时间：2018年8月20日 上午11:29:39
* 
*/
package com.tmall.server.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.exception.TmallParseBeanException;
import com.tmall.server.product.common.model.TmallStoreCategory;
import com.tmall.server.product.common.model.TmallStoreCategoryExample;
import com.tmall.server.product.common.model.TmallStoreCategoryExample.Criteria;
import com.tmall.server.product.dao.CategoryDao;
import com.tmall.server.product.dao.StoreCategoryDao;
import com.tmall.server.product.dao.TmallStoreCategoryDao;
import com.tmall.server.product.service.IStoreCategoryService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月20日 上午11:29:39
 */
@Service
public class StoreCategoryServiceImpl implements IStoreCategoryService
{
	@Autowired
	private StoreCategoryDao storeCategoryDao;

	@Override
	public List<CategoryDTO> findStoreAllTopLevelCategories(Long storeId)
	{
		TmallStoreCategoryExample example = new TmallStoreCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		criteria.andCategoryPidEqualTo(IStoreCategoryService.CATEGOTY_TOP_LEVEL);
		List<TmallStoreCategory> categories = storeCategoryDao.selectByExample(example);
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		// List<Integer>categoryIds=new ArrayList<>();
		if (categories != null && categories.size() > 0)
		{
			categories.forEach(c -> {
				CategoryDTO categoryDTO = new CategoryDTO();
				try
				{
					BeanUtils.copyProperties(categoryDTO, c);
					categoryDTOs.add(categoryDTO);
				} catch (Exception e)
				{
					throw new TmallParseBeanException(categories, c, TmallBussinessException.PARSE_BEAN_EXCEPTION,
							"将bean从TmallStoreCategory转为CategoryDTO的时候出错");
				}
				// categoryIds.add(c.getCategoryId());
			});
		}
		return categoryDTOs;
	}

}
