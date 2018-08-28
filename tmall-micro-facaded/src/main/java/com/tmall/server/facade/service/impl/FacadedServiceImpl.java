/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午9:44:59
* 
*/
package com.tmall.server.facade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.model.PageRequestDTO;
import com.joker.library.model.PageResponseDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.server.facade.service.IFacadedService;
import com.tmall.server.facade.service.ILoginService;
import com.tmall.server.spi.store.IStoreServerFeignService;

/**
* 
* @author joker 
* @date 创建时间：2018年8月28日 上午9:44:59
*/
@Service
public class FacadedServiceImpl implements IFacadedService
{	
	@Autowired
	private ILoginService loginService;
	@Autowired
	private IStoreServerFeignService storeServerFeignService;
	
	

	@Override
	public ResultDTO<String> loginAndAuth(String loginKey, String password, String storeAbbName)
	{
		return loginService.loginAndAuth(loginKey, password, storeAbbName);
	}

	@Override
	public ResultDTO<StoreDTO> findStoreDetail(Long storeId)
	{
		return storeServerFeignService.findStoreDetail(storeId);
	}

	@Override
	public ResultDTO<StoreDTO> findStoreByAbbName(String abbName)
	{
		return storeServerFeignService.findStoreByAbbName(abbName);
	}

	@Override
	public ResultDTO<PageResponseDTO<List<StoreDTO>>> findStoresByPage(PageRequestDTO pageRequestDTO)
	{
		return storeServerFeignService.findStoresByPage(pageRequestDTO);
	}

	@Override
	public ResultDTO<String> updateStoreStatus(Long storeId, Integer storeStatus)
	{
		return storeServerFeignService.updateStoreStatusByStoreId(storeId, storeStatus);
	}
	
}
