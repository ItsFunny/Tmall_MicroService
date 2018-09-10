/**
*
* @author joker 
* @date 创建时间：2018年8月27日 下午1:26:23
* 
*/
package com.tmall.server.spi.store.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.StoreDetail;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.spi.store.IStoreServerFeignService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月27日 下午1:26:23
 */
@Slf4j
@Component
public class StoreServerFeignFallback implements IStoreServerFeignService
{

	private Logger logger = LoggerFactory.getLogger(StoreServerFeignFallback.class);

	@Override
	public ResultDTO<PageResponseDTO<List<StoreDTO>>> findStoresByPage(PageRequestDTO pageRequestDTO)
	{
		logger.error("[获取所有店铺信息触发了服务降级]");
		return ResultUtils.fail("服务宕机了,请稍后再试");
	}

	@Override
	public ResultDTO<StoreDetail> findStoreDetail(Long storeId)
	{
		log.error("[findStoreDetail]获取店铺详情,触发了服务降级");
		return ResultUtils.fail("store服务宕机了,请稍后再试");
	}

	@Override
	public ResultDTO<StoreDTO> findStoreByAbbName(String storeAbbName)
	{
		log.error("[findStoreByAbbName]通过店铺缩写查找店铺触发了服务降级");
		return ResultUtils.fail("store服务宕机了,请稍后再试");
	}

	@Override
	public ResultDTO<String> updateStoreStatusByStoreId(Long storeId, Integer storeStatus)
	{
		log.error("[updateStoreStatusByStoreId] 更新店铺状态 触发了服务降级,可能是服务宕机了,storeId:{},storeStatus:{}", storeId,
				storeStatus);
		return ResultUtils.fail("store服务宕机了,请稍后再试");
	}

	@Override
	public ResultDTO<String> test()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultDTO<PageResponseDTO<List<BrandDTO>>> findBrandsByPage(PageRequestDTO pageRequestDTO)
	{
		log.error("[findBrandsByPage]分页查询brands,触发了服务降级,pageRequestDTO:{}", pageRequestDTO);
		return ResultUtils.fail("store服务宕机了,请稍后再试");
	}

	@Override
	public ResultDTO<String> addBrand(UserRequestDTO userRequestDTO)
	{
		log.error("[addBrand]添加品牌,触发了服务降级,brandDTO:{}", userRequestDTO);
		return ResultUtils.fail();
	}

	@Override
	public ResultDTO<BrandDTO> findBrandTypeById(Integer brandId)
	{
		log.error("[findById]通过id查询品牌,触发了服务降,brandId:{}", brandId);
		return ResultUtils.fail();
	}

}
