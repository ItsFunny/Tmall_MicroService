/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:44:03
* 
*/
package com.tmall.server.product.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.PropertyDTO;
import com.tmall.server.product.dao.PropertyDao;
import com.tmall.server.product.dao.count.PropertyCountDao;
import com.tmall.server.product.service.IProductServerPropertyService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月24日 下午6:44:03
 */
@Service
public class PropertyServiceImpl implements IProductServerPropertyService
{
	@Autowired
	private PropertyDao propertyDao;
	@Autowired
	private PropertyCountDao propertyCountDao;

	@Override
	public Collection<PropertyDTO> findPropertiesInCatIds(List<Integer> catIds)
	{
		Integer count = propertyCountDao.countPropertyInCatIds(catIds);
		if (count < 1)
		{
			return null;
		}
		return propertyDao.findPropertiesInCatIds(catIds);
	}

}
