/**
*
* @author joker 
* @date 创建时间：2018年6月23日 上午11:41:32
* 
*/
package com.tmall.server.product.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.BrandDTO;
import com.tmall.server.product.common.model.TmallStoreBrand;
import com.tmall.server.product.common.model.TmallStoreBrandExample;
import com.tmall.server.product.common.model.TmallStoreBrandExample.Criteria;
import com.tmall.server.product.dao.BrandDao;
import com.tmall.server.product.dao.count.BrandCountDao;
import com.tmall.server.product.service.IBrandService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月23日 上午11:41:32
 */
@Service
public class BrandServiceImpl implements IBrandService
{
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private BrandCountDao brandCountDao;

	@Override
	public Collection<BrandDTO> findAllBrands()
	{
		Integer countBrand = brandCountDao.countBrand();
		if (countBrand < 1)
		{
			return null;
		} else
		{
			return brandDao.findAll();
		}
	}

	@Override
	public List<BrandDTO> findStoreOperatedBrands(Long storeId)
	{
		TmallStoreBrandExample example = new TmallStoreBrandExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		List<TmallStoreBrand> brands = brandDao.selectByExample(example);
		if (null != brands && !brands.isEmpty())
		{
			List<BrandDTO> res = new ArrayList<BrandDTO>();
			brands.forEach(b -> {
				res.add(b.to());
			});
			return res;
		}
		return null;
	}

}
