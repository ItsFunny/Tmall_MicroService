/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午4:41:58
* 
*/
package com.tmall.server.store.provider.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.catalina.session.StoreBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.utils.JsonUtil;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.dto.RecordDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.service.AbstractPageService;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.store.common.exception.TmallStoreException;
import com.tmall.server.store.common.model.TmallBrand;
import com.tmall.server.store.common.model.TmallBrandExample;
import com.tmall.server.store.common.model.TmallBrandExample.Criteria;
import com.tmall.server.store.common.model.TmallBrandType;
import com.tmall.server.store.common.model.TmallStoreBrand;
import com.tmall.server.store.common.model.TmallStoreBrandExample;
import com.tmall.server.store.provider.dao.BrandDao;
import com.tmall.server.store.provider.dao.TmallBrandTypeDao;
import com.tmall.server.store.provider.dao.TmallStoreBrandDao;
import com.tmall.server.store.provider.service.IBrandService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月2日 下午4:41:58
 */
@Service
public class BrandServiceImpl implements IBrandService
{
	@Autowired
	private BrandDao brandDao;

	@Autowired
	private TmallStoreBrandDao storeBrandDao;

	@Autowired
	private TmallBrandTypeDao brandTypeDao;

	/*
	 * 找brands: 1.如果没有指定storeId,则默认为查询所有brands
	 * 2.如果指定了storeId,则先tmall_store_brand表中插叙所有符合条件的brands 2.1 然后拼接brandId 2.2
	 * 拼接好之后再在tmall_brand 中in 查询
	 */

	private class InnerPageServiceImpl extends AbstractPageService<List<BrandDTO>>
	{
		private Long countWithoutStore(Map<String, Object> condition)
		{
			TmallBrandExample example = new TmallBrandExample();
			return brandDao.countByExample(example);
		}

		private List<BrandDTO> parseBrand2DTO(List<TmallBrand> brands)
		{
			if (brands == null || brands.isEmpty())
			{
				return Collections.emptyList();
			} else
			{
				List<BrandDTO> brandDTOs = new ArrayList<>();
				brands.forEach(b -> {
					brandDTOs.add(b.to());
				});
				return brandDTOs;
			}
		}

		@Override
		public Long countByCondition(Map<String, Object> condition)
		{
			if (null == condition || !condition.containsKey("storeId"))
			{
				return countWithoutStore(condition);
			}
			TmallStoreBrandExample storeBrandExample = new TmallStoreBrandExample();
			Object storeId = condition.get("storeId");
			if (null == storeId)
			{
				throw new TmallStoreException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.MISSING_ARGUMENT, "storeId不能为空"));
			}
			storeBrandExample.or().andStoreIdEqualTo(Long.parseLong(storeId.toString()));
			return storeBrandDao.countByExample(storeBrandExample);
		}

		@Override
		public List<BrandDTO> findByCondition(Integer start, Integer end, Map<String, Object> condition)
		{
			TmallStoreBrandExample storeBrandExample = new TmallStoreBrandExample();
			com.tmall.server.store.common.model.TmallStoreBrandExample.Criteria storeCriteria = storeBrandExample
					.createCriteria();
			storeBrandExample.setStart(start);
			storeBrandExample.setEnd(end);
			// 这里还会有其他条件的,但是针对这个表的话,如果调用这个方法,则storeId是必须存在的
			storeCriteria.andStoreIdEqualTo(Long.parseLong(condition.get("storeId").toString()));
			List<TmallStoreBrand> storeBrands = storeBrandDao.selectByExample(storeBrandExample);
			if (null != storeBrands && !storeBrands.isEmpty())
			{
				List<Integer> brandIdList = new ArrayList<Integer>();
				storeBrands.forEach(s -> {
					brandIdList.add(s.getBrandId());
				});
				TmallBrandExample example = new TmallBrandExample();
				Criteria criteria = example.createCriteria();
				criteria.andBrandIdIn(brandIdList);
				List<TmallBrand> brands = brandDao.selectByExample(example);
				List<BrandDTO> dtos = parseBrand2DTO(brands);
				brands = null;
				return dtos;
			} else
				return Collections.emptyList();
		}

		@Override
		public List<BrandDTO> findAllByPage(Integer start, Integer end)
		{
			TmallBrandExample example = new TmallBrandExample();
			example.setStart(start);
			example.setEnd(end);
			List<TmallBrand> brands = brandDao.selectByExample(example);
			List<BrandDTO> dtos = parseBrand2DTO(brands);
			brands = null;
			return dtos;
		}
	}

	@Override
	public PageResponseDTO<List<BrandDTO>> findByPage(PageRequestDTO pageRequestDTO)
	{
		InnerPageServiceImpl innerPageServiceImpl = this.new InnerPageServiceImpl();
		PageResponseDTO<List<BrandDTO>> dto = innerPageServiceImpl.findByPage(pageRequestDTO);
		innerPageServiceImpl = null;
		return dto;
	}

	public Integer add(TmallBrand brand)
	{
		return brandDao.insertSelective(brand);
	}

	@Override
	public BrandDTO findBrandTypeById(Integer brandId)
	{
		TmallBrandType type = brandTypeDao.selectByPrimaryKey(brandId);

		if (null == type)
		{
			return null;
		} else
		{
			return type.to();
		}
	}

	@Override
	public Integer updateSelective(TmallBrand brand)
	{
		TmallBrandExample example = new TmallBrandExample();
		Criteria criteria = example.createCriteria();
		criteria.andBrandIdEqualTo(brand.getBrandId());
		return brandDao.updateByExampleSelective(brand, example);
	}

	@Override
	public TmallBrand findById(Integer brandId)
	{

		TmallBrandExample example = new TmallBrandExample();
		Criteria criteria = example.createCriteria();
		criteria.andBrandIdEqualTo(brandId);
		TmallBrand brand = brandDao.selectByPrimaryKey(brandId);
		return brand;
	}

	@Override
	public Integer deleteById(Integer brandId)
	{
		return brandDao.deleteByPrimaryKey(brandId);
	}

	@Override
	public Integer deleteByIdInBatch(List<Integer> idList)
	{
		if(idList==null||idList.isEmpty())
		{
			throw new TmallStoreException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.STORE_MISSING_ARGUMENT, "品牌id不能为空"));
		}
		TmallBrandExample example=new TmallBrandExample();
		Criteria criteria = example.createCriteria();
		criteria.andBrandIdIn(idList);
		return brandDao.deleteByExample(example);
	}

}
