/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午12:04:51
* 
*/
package com.tmall.facade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.facade.service.IBrandService;
import com.tmall.server.spi.store.IStoreServerFeignService;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 下午12:04:51
*/
@Service
public class FacadedBrandServiceImpl implements IBrandService
{
	@Autowired
	private IStoreServerFeignService storeServerFeignService;

	@Override
	public ResultDTO<BrandDTO> findBrandTypeById(Integer brandTypeId)
	{
		return storeServerFeignService.findBrandTypeById(brandTypeId);
	}

}
