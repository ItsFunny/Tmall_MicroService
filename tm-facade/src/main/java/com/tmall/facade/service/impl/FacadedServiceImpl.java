/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午9:44:59
* 
*/
package com.tmall.facade.service.impl;

import java.util.List;
import java.util.Map;

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
import com.tmall.common.dto.TmallConfigTemplateDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.facade.service.IBrandService;
import com.tmall.facade.service.IFacadedService;
import com.tmall.facade.service.ILoginService;
import com.tmall.facade.service.IMessageService;
import com.tmall.server.spi.auth.IAuthFeignService;
import com.tmall.server.spi.message.IMessageServerFeignService;
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
	private IAuthFeignService authFeignService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IBrandService brandService;
	
	

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
	public ResultDTO<List<TmallConfigTemplateDTO>> getConfigTemplates(Map<String, Object> conditions)
	{
		return authFeignService.getConfigTemplates(conditions);
	}
	@Override
	public ResultDTO<Object> addMessageJob(MessageDTO messageDTO)
	{
		return messageService.addMessageJob(messageDTO);
	}


	@Override
	public ResultDTO<String> updateMessageStatus(String messageId, Integer status)
	{
		return messageService.updateMessageStatus(messageId, status);
	}

	@Override
	public ResultDTO<BrandDTO> findBrandTypeById(Integer brandTypeId)
	{
		return brandService.findBrandTypeById(brandTypeId);
	}

	@Override
	public ResultDTO<String> deleteBrandsInBatch(UserRequestDTO userRequestDTO)
	{
		return brandService.deleteBrandsInBatch(userRequestDTO);
	}
	
}
