/**
*
* @author joker 
* @date 创建时间：2018年8月4日 下午7:23:17
* 
*/
package com.tmall.server.store.provider.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.page.PageResponseDTO;
import com.joker.library.utils.PageResultUtil;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.service.TmallObserverable;
import com.tmall.server.store.common.model.TmallStore;
import com.tmall.server.store.common.model.TmallStoreExample;
import com.tmall.server.store.common.model.TmallStoreExample.Criteria;
import com.tmall.server.store.provider.dao.StoreDao;
import com.tmall.server.store.provider.service.IStoreService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月4日 下午7:23:17
 */
@Service
public class StoreServiceImpl extends TmallObserverable implements IStoreService
{
	@Autowired
	private StoreDao storeDao;

	@Override
	public TmallStore findByStoreAbbName(String storeName)
	{
		TmallStoreExample example = new TmallStoreExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreAbbNameEqualTo(storeName);
		List<TmallStore> store = storeDao.selectByExample(example);
		if (store == null || store.isEmpty())
		{
			return null;
		} else if (store.size() == 1)
		{
			return store.iterator().next();
		} else
		{
			throw new RuntimeException("find multi stores");
		}
	}

	@Override
	public PageResponseDTO<List<StoreDTO>> findByPage(int pageSize, int pageNum, Map<String, Object> conditions)
	{
		if (pageSize < 0)
		{
			pageSize = 10;
		}
		if (pageNum < 1)
		{
			pageNum = 1;
		}
		TmallStoreExample example = new TmallStoreExample();
		Criteria criteria = example.createCriteria();
		example.setStart((pageNum - 1) * pageSize);
		example.setEnd(pageSize);
		if (conditions != null)
		{
			if (conditions.containsKey("status"))
			{
				criteria.andStoreStatusEqualTo(Integer.parseInt(conditions.get("status").toString()));
			}
			if (conditions.containsKey("storeName"))
			{
				example.or().andStoreNameLike("%" + conditions.get("storeName").toString() + "%");
			}
			if (conditions.containsKey("storeAbbName"))
			{
				example.or().andStoreAbbNameLike("%" + conditions.get("storeAbbName") + "%");
			}
		}
		Long totalCount = countStores(conditions);
		if (totalCount <= 0)
		{
			return PageResultUtil.emptyPage();
		}
		List<StoreDTO> stores = new ArrayList<StoreDTO>();

		List<TmallStore> storeList = storeDao.selectByExample(example);
		if (null != storeList)
		{
			storeList.forEach(s -> {
				stores.add(s.to());
			});
		}
		PageResponseDTO<List<StoreDTO>> responseDTO = new PageResponseDTO<List<StoreDTO>>(stores, pageSize, pageNum,
				totalCount);
		return responseDTO;
	}

	@Override
	public Long countStores(Map<String, Object> params)
	{
		TmallStoreExample example = new TmallStoreExample();
		if (null==params||params.isEmpty())
		{
			return storeDao.countStores();
		}
		if (params.containsKey("status"))
		{
			example.or().andStoreStatusEqualTo(Integer.valueOf(params.get("status").toString()));
		}
		if (params.containsKey("storeName"))
		{
			example.or().andStoreNameLike("%" + params.get("storeName").toString() + "%");
		}
		if (params.containsKey("storeAbbName"))
		{
			// criteria.andStoreAbbNameLike(params.get("storeAbbName").toString());
			example.or().andStoreAbbNameLike("%" + params.get("storeAbbName") + "%");
		}
		return storeDao.countStoresByExample(example);
	}

	@Override
	public Integer updateStoreStatusByStoreId(Long storeId, Integer status)
	{
		TmallStoreExample example = new TmallStoreExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		TmallStore store = new TmallStore();
		store.setStoreStatus(status);
		return storeDao.updateByExampleSelective(store, example);
	}

	@Override
	public TmallStore findByStoreId(Long storeId)
	{
		return storeDao.selectByPrimaryKey(storeId);
	}

}
