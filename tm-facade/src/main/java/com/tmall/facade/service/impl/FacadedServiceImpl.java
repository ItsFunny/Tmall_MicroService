/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午9:44:59
* 
*/
package com.tmall.facade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.StoreDetail;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.facade.service.IFacadedService;
import com.tmall.facade.service.ILoginService;
import com.tmall.message.spi.IMessageServerFeignService;
import com.tmall.server.spi.product.IBrandServerFeignService;
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
	@Autowired
	private IMessageServerFeignService messageServerFeignService;
	
	

	@Override
	public ResultDTO<String> loginAndAuth(String loginKey, String password, String storeAbbName)
	{
		return loginService.loginAndAuth(loginKey, password, storeAbbName);
	}

	@Override
	public ResultDTO<StoreDetail> findStoreDetail(Long storeId)
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

	@Override
	public ResultDTO<PageResponseDTO<List<BrandDTO>>> findBrandsByPage(PageRequestDTO pageRequestDTO)
	{
		return storeServerFeignService.findBrandsByPage(pageRequestDTO);
	}

	@Override
	public ResultDTO<String> addBrand(UserRequestDTO userRequestDTO)
	{
		return storeServerFeignService.addBrand(userRequestDTO);
	}

	@Override
	public ResultDTO<Object> addMessageJob(MessageDTO messageDTO)
	{
		return messageServerFeignService.addMessageJob(messageDTO);
	}
	
}
